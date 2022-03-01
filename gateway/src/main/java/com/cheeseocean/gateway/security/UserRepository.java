package com.cheeseocean.gateway.security;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.cheeseocean.common.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

}
