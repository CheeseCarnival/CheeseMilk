package com.cheeseocean.community;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CommunityApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .main(CommunityApplication.class)
                .run(args);
    }
}
