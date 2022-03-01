package com.cheeseocean.gateway.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.cheeseocean.common.entity.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByUsername(String name);
}
