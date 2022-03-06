package com.cheeseocean.gateway.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends R2dbcRepository<User, Long> {

    Flux<User> findByUsername(String name);
}
