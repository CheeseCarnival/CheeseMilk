package com.cheeseocean.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisWebSession
public class GatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(GatewayApplication.class)
                .run(args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @RestController
    static class TestController{


        @Autowired
        private RestTemplate restTemplate;
        @Autowired
        private LoadBalancerClient loadBalancerClient;

        @Value("${spring.application.name}")
        private String appName;

        @GetMapping("/echo/app-name")
        public String echoAppName(){
            //Access through the combination of LoadBalanceClient and RestTemplate
            ServiceInstance serviceInstance = loadBalancerClient.choose("core");
            String path = String.format("http://%s:%s/echo/%s",serviceInstance.getHost(),serviceInstance.getPort(),appName);
            System.out.println("request path:" +path);
            return restTemplate.getForObject(path,String.class);
        }
    }
}
