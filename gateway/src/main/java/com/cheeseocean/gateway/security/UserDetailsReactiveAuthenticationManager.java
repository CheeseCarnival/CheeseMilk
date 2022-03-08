/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cheeseocean.gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class UserDetailsReactiveAuthenticationManager
        implements ReactiveAuthenticationManager, MessageSourceAware {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private ReactiveUserDetailsPasswordService userDetailsPasswordService;

    private ReactiveUserDetailsService userDetailsService;

    private Scheduler scheduler = Schedulers.boundedElastic();

    private UserDetailsChecker preAuthenticationChecks = this::defaultPreAuthenticationChecks;

    private UserDetailsChecker postAuthenticationChecks = this::defaultPostAuthenticationChecks;

    private void defaultPreAuthenticationChecks(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            this.logger.debug("User account is locked");
            throw new LockedException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked",
                    "User account is locked"));
        }
        if (!user.isEnabled()) {
            this.logger.debug("User account is disabled");
            throw new DisabledException(
                    this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
        }
        if (!user.isAccountNonExpired()) {
            this.logger.debug("User account is expired");
            throw new AccountExpiredException(this.messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
        }
    }

    private void defaultPostAuthenticationChecks(UserDetails user) {
        if (!user.isCredentialsNonExpired()) {
            this.logger.debug("User account credentials have expired");
            throw new CredentialsExpiredException(this.messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.credentialsExpired", "User credentials have expired"));
        }
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String username = authentication.getName();
        String presentedPassword = (String) authentication.getCredentials();
        Object details = authentication.getDetails();
        // @formatter:off
        return retrieveUser(username, details)
                .doOnNext(this.preAuthenticationChecks::check)
                .publishOn(this.scheduler)
                .filter((userDetails) -> this.passwordEncoder.matches(presentedPassword, userDetails.getPassword()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BadCredentialsException("Invalid Credentials"))))
                .flatMap((userDetails) -> upgradeEncodingIfNecessary(userDetails, presentedPassword))
                .doOnNext(this.postAuthenticationChecks::check)
                .map(this::createUsernamePasswordAuthenticationToken);
        // @formatter:on
    }

    private Mono<UserDetails> upgradeEncodingIfNecessary(UserDetails userDetails, String presentedPassword) {
        boolean upgradeEncoding = this.userDetailsPasswordService != null
                && this.passwordEncoder.upgradeEncoding(userDetails.getPassword());
        if (upgradeEncoding) {
            String newPassword = this.passwordEncoder.encode(presentedPassword);
            return this.userDetailsPasswordService.updatePassword(userDetails, newPassword);
        }
        return Mono.just(userDetails);
    }

    private UsernamePasswordAuthenticationToken createUsernamePasswordAuthenticationToken(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    /**
     * The {@link PasswordEncoder} that is used for validating the password. The default
     * is {@link PasswordEncoderFactories#createDelegatingPasswordEncoder()}
     * @param passwordEncoder the {@link PasswordEncoder} to use. Cannot be null
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
    }

    public void setScheduler(Scheduler scheduler) {
        Assert.notNull(scheduler, "scheduler cannot be null");
        this.scheduler = scheduler;
    }

    public void setUserDetailsPasswordService(ReactiveUserDetailsPasswordService userDetailsPasswordService) {
        this.userDetailsPasswordService = userDetailsPasswordService;
    }

    public void setUserDetailsService(ReactiveUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setPostAuthenticationChecks(UserDetailsChecker postAuthenticationChecks) {
        Assert.notNull(this.postAuthenticationChecks, "postAuthenticationChecks cannot be null");
        this.postAuthenticationChecks = postAuthenticationChecks;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        Assert.notNull(messageSource, "messageSource cannot be null");
        this.messages = new MessageSourceAccessor(messageSource);
    }

    protected Mono<UserDetails> retrieveUser(String username, Object details) {
        return userDetailsService.findByUsernameAndDetails(username, details);
    }
}
