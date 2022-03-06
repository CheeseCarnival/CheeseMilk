package com.cheeseocean.gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cheeseocean.gateway.repository.RoleRepository;
import com.cheeseocean.gateway.repository.User;
import com.cheeseocean.gateway.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class R2dbcReactiveUserDetailsService implements ReactiveUserDetailsService, ReactiveUserDetailsPasswordService {

    private static Logger logger = LoggerFactory.getLogger(R2dbcReactiveUserDetailsService.class);
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username).single().map(this::convertToUserDetails);
    }

    private UserDetails convertToUserDetails(User user) {
//        Mono<List<Role>> roles =  roleRepository.findRolesByUserId(user.getId());

        logger.info("username:{}, password: {}", user.getUsername(), user.getPassword());
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    @Override
    public Mono<UserDetails> updatePassword(UserDetails user, String newPassword) {
        return Mono.empty();
    }
}
