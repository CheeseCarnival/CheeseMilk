package com.cheeseocean.community;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.cheeseocean.common.util.CheeseOceanBanner;

@SpringBootApplication
@EnableDiscoveryClient
public class CommunityApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .banner(new CheeseOceanBanner())
                .sources(CommunityApplication.class)
                .run(args);
    }
}
