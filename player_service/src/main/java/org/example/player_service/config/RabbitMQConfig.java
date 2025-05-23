package org.example.player_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PLAYER_CREATED_QUEUE = "player-created-queue";
    public static final String SHOT_FIRED_QUEUE = "shot-fired-queue";

    @Bean
    public Queue playerCreatedQueue() {
        return new Queue(PLAYER_CREATED_QUEUE, false);
    }

    @Bean
    public Queue shotFiredQueue(){
        return new Queue(SHOT_FIRED_QUEUE,false);
    }
}
