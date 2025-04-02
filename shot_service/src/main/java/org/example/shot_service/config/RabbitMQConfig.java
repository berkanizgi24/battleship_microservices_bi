package org.example.shot_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {
    public static final String SHOT_FIRED_QUEUE = "shot-fired-queue";

    @Bean
    public Queue playerCreatedQueue() {
        return new Queue(SHOT_FIRED_QUEUE, false);
    }
}
