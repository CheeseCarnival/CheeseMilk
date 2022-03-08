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
                .map(body -> convertToAuthentication(((LoginEntity) body)))
                .single()
                .cast(Authentication.class);
    }

    private Authentication convertToAuthentication(LoginEntity loginEntity){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginEntity.getIdentifier(), loginEntity.getCredential());
        LoginAuthenticationDetails details = new LoginAuthenticationDetails();
        details.setIdentifyType(loginEntity.getType());
        token.setDetails(details);
        return token;
    }
}
