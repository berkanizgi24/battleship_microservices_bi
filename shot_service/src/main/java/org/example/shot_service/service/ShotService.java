package org.example.shot_service.service;

import org.example.shot_service.dto.ShipDTO;
import org.example.shot_service.model.Shot;
import org.example.shot_service.repository.ShotRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ShotService {
    private final RestTemplate restTemplate;
    private final ShotRepository shotRepository;

    public ShotService(ShotRepository shotRepository) {
        this.shotRepository = shotRepository;
        this.restTemplate = new RestTemplate();
    }

    public Shot placeShot(Long attackerId, Long defenderId, int x, int y) {
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
        return shotRepository.save(shot);
    }


    public List<Shot> getShootsByPlayerId(Long playerId){
        return shotRepository.findByPlayerId(playerId);
    }
}
