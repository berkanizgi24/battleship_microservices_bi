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
            return   circuitBreakerFactory.create("createGameBreaker").run(() ->
            restTemplate.postForObject("http://localhost:8081/games", null, GameDTO.class)
        );
    }

    public List<PlayerDTO> getPlayersInGame(Long gameId) {
        return circuitBreakerFactory.create("playersInGameBreaker").run(() -> {
            ResponseEntity<PlayerDTO[]> response = restTemplate.getForEntity("http://localhost:8081/games/" + gameId + "/players", PlayerDTO[].class);
            return List.of(response.getBody());
        });
    }

    public List<PlayerDTO> getPlayers(Long gameId) {
        return circuitBreakerFactory.create("getPlayersBreaker").run(() -> {
            ResponseEntity<PlayerDTO[]> response = restTemplate.getForEntity("http://localhost:8082/players?gameId=" + gameId, PlayerDTO[].class);
            return List.of(response.getBody());
        });
    }

    public PlayerDTO createPlayer(Long gameId, String name) {
        return circuitBreakerFactory.create("createPlayerBreaker").run(() -> {
        String url = "http://localhost:8082/players?gameId=" + gameId + "&name=" + name;
        return restTemplate.postForObject(url, null, PlayerDTO.class);
    });}


    public List<ShipDTO> getShips(Long playerId) {
        return circuitBreakerFactory.create("getShipsBreaker").run(() -> {
            ResponseEntity<ShipDTO[]> response = restTemplate.getForEntity("http://localhost:8082/ships/" + playerId, ShipDTO[].class);
            return List.of(response.getBody());
        });
    }

    public ShipDTO placeShip(Long playerId, int x, int y, int length, boolean isHorizontal) {
        return circuitBreakerFactory.create("placeShipBreaker").run(() -> {
            String url = String.format("http://localhost:8082/ships?playerId=%d&x=%d&y=%d&length=%d&isHorizontal=%s",
                    playerId, x, y, length, isHorizontal);
            return restTemplate.postForObject(url, null, ShipDTO.class);
        });
    }

    public ShotDTO placeShot(Long attackerId, Long defenderId, int x, int y) {
        return circuitBreakerFactory.create("placeShotBreaker").run(() -> {
        String url = String.format("http://localhost:8083/shots?attackerId=%d&defenderId=%d&x=%d&y=%d",
                attackerId, defenderId, x, y);
        return restTemplate.postForObject(url, null, ShotDTO.class);
    });}

    public void deleteGame(Long gameId){
             circuitBreakerFactory.create("deleteGameBreaker").run(() -> {
            restTemplate.delete("http://localhost:8081/games/" + gameId);
            return null;
        });
        }


    public void deletePlayer(Long playerId){
        circuitBreakerFactory.create("deletePlayerBreaker").run(() -> {
        restTemplate.delete("http://localhost:8082/players/" + playerId);
        return null;
    });}

    public void deleteShip(Long shipId){
        circuitBreakerFactory.create("deleteShipBreaker").run(() -> {
        restTemplate.delete("http://localhost:8081/ships/" + shipId);
        return null;
    });}

    public void deleteShot(Long shotId){
        circuitBreakerFactory.create("deleteShotBreaker").run(() -> {
        restTemplate.delete("http://localhost:8082/shots/" + shotId);
        return null;
    });}
}


