package com.cheeseocean.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

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
                .formLogin(Customizer.withDefaults());
        // @formatter:on
        return http.build();
    }

    @Bean
    MapReactiveUserDetailsService userDetailsService() {
        // @formatter:off
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("qisee")
                .password("www.qisee.cn")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN", "USER")
                .build();
        // @formatter:on
        return new MapReactiveUserDetailsService(user, admin);
    }

}
