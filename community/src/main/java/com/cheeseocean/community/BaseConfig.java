package com.cheeseocean.community;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
@EntityScan("com.cheeseocean.community.entity")
@EnableJpaRepositories(basePackages = "com.cheeseocean.community.repository")
@EnableJpaAuditing
@EnableDubbo(scanBasePackages = "com.cheeseocean.community.service")
public class BaseConfig {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("cheeseocean"));
    }

}
