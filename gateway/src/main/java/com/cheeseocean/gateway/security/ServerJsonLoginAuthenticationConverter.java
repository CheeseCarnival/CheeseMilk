package com.cheeseocean.gateway.security;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ResolvableType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class ServerJsonLoginAuthenticationConverter implements ServerAuthenticationConverter {

    private static Logger logger = LoggerFactory.getLogger(ServerJsonLoginAuthenticationConverter.class);

    private Jackson2JsonDecoder decoder = new Jackson2JsonDecoder();

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        return decoder
                .decode(request.getBody(),
                        ResolvableType.forType(LoginEntity.class),
                        null,
                        Collections.emptyMap())
                .map(body -> new UsernamePasswordAuthenticationToken(((LoginEntity) body).getUsername(), ((LoginEntity) body).getPassword()))
                .single()
                .cast(Authentication.class);
    }

    public static class LoginEntity {
        private String username;
        private String password;

        public LoginEntity() {

        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public String getUsername() {
            return username;
        }
    }
}
