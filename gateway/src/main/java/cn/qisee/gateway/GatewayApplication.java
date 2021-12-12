package cn.qisee.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisWebSession
public class GatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(GatewayApplication.class)
                .run(args);
    }

}
