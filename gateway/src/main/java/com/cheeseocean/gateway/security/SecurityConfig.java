package com.cheeseocean.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // @formatter:off
        http.authorizeExchange((authorize) -> {
            authorize.pathMatchers("/token").permitAll()
                    .anyExchange().authenticated();
        })
                .httpBasic().and()
                .oauth2Login().and()
                .formLogin()
                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/core/user"));
        // @formatter:on
        return http.build();
    }

    @Bean
    MapReactiveUserDetailsService userDetailsService() {
        // @formatter:off
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN", "USER")
                .build();
        // @formatter:on
        return new MapReactiveUserDetailsService(user, admin);
    }

}
