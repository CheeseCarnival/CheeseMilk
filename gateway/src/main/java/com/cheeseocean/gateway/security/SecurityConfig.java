package com.cheeseocean.gateway.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private R2dbcReactiveUserDetailsService userDetailsService;

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // @formatter:off
        http.authorizeExchange((authorize) -> {
                    authorize.pathMatchers(HttpMethod.POST, "/login").permitAll()
                            .anyExchange().authenticated();
                })
                .httpBasic().and()
//                .oauth2Login().and()
                .cors(corsSpec -> {
                    corsSpec.configurationSource(corsConfigurationSource());
                })
                .csrf().disable();
//                .loginPage("/login")
//                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/core/user/info"));
        http.addFilterAt(loginFilter(), SecurityWebFiltersOrder.FORM_LOGIN);
        // @formatter:on
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    ReactiveAuthenticationManager authenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setUserDetailsPasswordService(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder());
        return authenticationManager;
    }

    @Bean
    AuthenticationWebFilter loginFilter() {
        AuthenticationWebFilter loginFilter = new AuthenticationWebFilter(authenticationManager());
        loginFilter.setServerAuthenticationConverter(exchange -> exchange.getFormData().map(data -> {
            String username = data.getFirst("username");
            String password = data.getFirst("password");
            return new UsernamePasswordAuthenticationToken(username, password);
        }));
        loginFilter.setSecurityContextRepository(new WebSessionServerSecurityContextRepository());
        loginFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login"));
        loginFilter.setAuthenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/core/user"));
        loginFilter.setAuthenticationFailureHandler(new RedirectServerAuthenticationFailureHandler("/login?error"));
        return loginFilter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//    @Bean
//    MapReactiveUserDetailsService userDetailsService() {
//        // @formatter:off
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN", "USER")
//                .build();
//        // @formatter:on
//        return new MapReactiveUserDetailsService(user, admin);
//    }

}
