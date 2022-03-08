package com.cheeseocean.gateway.security;

import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

public interface ReactiveUserDetailsService {

    Mono<UserDetails> findByUsernameAndDetails(String username, Object details);
}
