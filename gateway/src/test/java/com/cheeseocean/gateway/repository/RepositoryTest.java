package com.cheeseocean.gateway.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

@DataR2dbcTest
public class RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSelect() {
        userRepository.findByUsername("xxxcrel")
                .doOnNext(user -> {
                    System.out.println(user.getUsername());
                }).subscribe();
    }
}
