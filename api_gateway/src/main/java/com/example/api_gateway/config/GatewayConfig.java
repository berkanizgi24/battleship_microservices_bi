package com.example.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("game_service", r -> r.path("/games/**")
                        .uri("lb://game-service"))

                .route("player_service", r -> r.path("/players/**")
                        .uri("lb://player-service"))

                .route("shot_service", r -> r.path("/shots/**")
                        .uri("lb://shot-service"))

                .build();
    }
}
