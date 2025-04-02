package org.example.shot_service.service;

import org.example.shot_service.config.RabbitMQConfig;
import org.example.shot_service.dto.ShipDTO;
import org.example.shot_service.model.Shot;
import org.example.shot_service.repository.ShotRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ShotService {
    private final RestTemplate restTemplate;
    private final ShotRepository shotRepository;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    private final RabbitTemplate rabbitTemplate;

    public ShotService(ShotRepository shotRepository, Resilience4JCircuitBreakerFactory circuitBreakerFactory, RabbitTemplate rabbitTemplate) {
        this.shotRepository = shotRepository;
        this.restTemplate = new RestTemplate();
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Shot placeShot(Long attackerId, Long defenderId, int x, int y) {
        return circuitBreakerFactory.create("placeShotBreaker").run(() -> {
            String shipServiceUrl = "http://localhost:8082/ships/" + defenderId;
            ShipDTO[] ships = restTemplate.getForObject(shipServiceUrl, ShipDTO[].class);

            boolean hit = false;
            if (ships != null) {
                for (ShipDTO ship : ships) {
                    if (ship.isHorizontal()) {
                        if (y == ship.y() && x >= ship.x() && x < ship.x() + ship.length()) {
                            hit = true;
                            break;
                        }
                    } else {
                        if (x == ship.x() && y >= ship.y() && y < ship.y() + ship.length()) {
                            hit = true;
                            break;
                        }
                    }
                }
            }
            Shot shot = new Shot(attackerId, x, y, hit);
            Shot saved = shotRepository.save(shot);

            rabbitTemplate.convertAndSend(RabbitMQConfig.SHOT_FIRED_QUEUE, "Schuss abgefeuert");
            return saved;
        });
    }


    public List<Shot> getShootsByPlayerId(Long playerId){
        return shotRepository.findByPlayerId(playerId);
    }

    public void deleteShot(Long shotId){
        shotRepository.deleteById(shotId);
    }
}
