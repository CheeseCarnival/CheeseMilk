package com.cheeseocean.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.cheeseocean.core")
@EnableCaching
@EnableJpaAuditing
@EntityScan(basePackages = "com.cheeseocean.common.entity")
@EnableJpaRepositories(basePackages = "com.cheeseocean.common.repository")
@EnableDiscoveryClient
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

    @RestController
    static class TestController{

        @GetMapping("/user/info")
        public String echo(){
            return "admin";
        }
    }
}
