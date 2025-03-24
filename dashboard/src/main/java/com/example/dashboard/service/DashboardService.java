package com.example.dashboard.service;

import com.example.dashboard.dto.GameDTO;
import com.example.dashboard.dto.PlayerDTO;
import com.example.dashboard.dto.ShipDTO;
import com.example.dashboard.dto.ShotDTO;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DashboardService {
    private final RestTemplate restTemplate;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    public DashboardService(RestTemplate restTemplate, Resilience4JCircuitBreakerFactory resilience4JCircuitBreakerFactory) {
        this.restTemplate = restTemplate;
        this.circuitBreakerFactory = resilience4JCircuitBreakerFactory;
    }

    public List<GameDTO> getAllGames() {
        return circuitBreakerFactory.create("getAllGamesBreaker").run(() -> {
            ResponseEntity<GameDTO[]> response = restTemplate.getForEntity("http://localhost:8081/games", GameDTO[].class);
            return List.of(response.getBody());
        });
    }

    public GameDTO createGame() {
        return restTemplate.postForObject("http://localhost:8081/games", null, GameDTO.class);
    }

    public List<PlayerDTO> getPlayersInGame(Long gameId) {
        ResponseEntity<PlayerDTO[]> response = restTemplate.getForEntity("http://localhost:8081/games/" + gameId + "/players", PlayerDTO[].class);
        return List.of(response.getBody());
    }

    public List<PlayerDTO> getPlayers(Long gameId) {
        ResponseEntity<PlayerDTO[]> response = restTemplate.getForEntity("http://localhost:8082/players?gameId=" + gameId, PlayerDTO[].class);
        return List.of(response.getBody());
    }

    public PlayerDTO createPlayer(Long gameId, String name) {
        String url = "http://localhost:8082/players?gameId=" + gameId + "&name=" + name;
        return restTemplate.postForObject(url, null, PlayerDTO.class);
    }

    public List<ShipDTO> getShips(Long playerId) {
        ResponseEntity<ShipDTO[]> response = restTemplate.getForEntity("http://localhost:8082/ships/" + playerId, ShipDTO[].class);
        return List.of(response.getBody());
    }

    public ShipDTO placeShip(Long playerId, int x, int y, int length, boolean isHorizontal) {
        String url = String.format("http://localhost:8082/ships?playerId=%d&x=%d&y=%d&length=%d&isHorizontal=%s",
                playerId, x, y, length, isHorizontal);
        return restTemplate.postForObject(url, null, ShipDTO.class);
    }

    public ShotDTO placeShot(Long attackerId, Long defenderId, int x, int y) {
        String url = String.format("http://localhost:8083/shots?attackerId=%d&defenderId=%d&x=%d&y=%d",
                attackerId, defenderId, x, y);
        return restTemplate.postForObject(url, null, ShotDTO.class);
    }

    public void deleteGame(Long gameId){
        restTemplate.delete("http://localhost:8081/games/" + gameId);
    }

    public void deletePlayer(Long playerId){
        restTemplate.delete("http://localhost:8082/players/" + playerId);
    }

    public void deleteShip(Long shipId){
        restTemplate.delete("http://localhost:8081/ships/" + shipId);
    }

    public void deleteShot(Long shotId){
        restTemplate.delete("http://localhost:8082/shots/" + shotId);
    }
}


