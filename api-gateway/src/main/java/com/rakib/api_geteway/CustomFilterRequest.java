package com.rakib.api_geteway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class CustomFilterRequest implements GlobalFilter {
    private final Logger logger = LoggerFactory.getLogger(CustomFilterRequest.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("From Pre-Filter");
        ServerHttpRequest request = exchange.getRequest();
        //Apply Business Logic
        logger.info("HTTP request: "+request.getRemoteAddress());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            System.out.println("From Post-Filter");
            ServerHttpResponse response = exchange.getResponse();
            //Apply Business Logic
            logger.info("Response Status Code: "+ response.getStatusCode());
        }));
    }
}
