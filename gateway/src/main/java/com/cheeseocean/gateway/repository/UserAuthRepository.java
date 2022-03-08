package com.cheeseocean.gateway.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserAuthRepository extends R2dbcRepository<UserAuth, Long> {

    Mono<UserAuth> findByIdentifyTypeAndIdentifier(String type, String identifier);
}
