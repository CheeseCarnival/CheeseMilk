package com.cheeseocean.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cheeseocean.common.exception.ExpectedException;
import com.cheeseocean.gateway.repository.UserAuthWithRole;
import com.cheeseocean.gateway.repository.UserAuthRepository;

import reactor.core.publisher.Mono;

@Service
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService, ReactiveUserDetailsPasswordService {


    private UserAuthRepository userAuthRepository;

    @Autowired
    public void setRoleRepository(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public Mono<UserDetails> findByUsernameAndDetails(String username, Object details) {
        return userAuthRepository.findUserDetailsByIdentifierAndType(username, ((LoginAuthenticationDetails) details).getIdentifyType())
                .map(detailsList -> User.builder()
                        .username(detailsList.get(0).getIdentifier())
                        .password(detailsList.get(0).getCredential())
                        .roles(detailsList.stream().map(UserAuthWithRole::getRoleName).toArray(String[]::new))
                        .build());
    }

    @Override
    public Mono<UserDetails> updatePassword(UserDetails user, String newPassword) {
        return null;
    }
}
