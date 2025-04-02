package org.example.game_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {
    public static final String PLAYER_CREATED_QUEUE = "player-created-queue";

    @Bean
    public Queue playerCreatedQueue() {
        return new Queue(PLAYER_CREATED_QUEUE, false);
    }
}
