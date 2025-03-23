package org.example.game_service.service;

import org.example.game_service.dto.PlayerDTO;
import org.example.game_service.model.Game;
import org.example.game_service.repository.GameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final RestTemplate restTemplate;
    public GameService(GameRepository gameRepository, RestTemplate restTemplate) {
        this.gameRepository = gameRepository;
        this.restTemplate = restTemplate;
    }

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public Game createGame(){
        Game game = new Game();
        return gameRepository.save(game);
    }

    public Optional<Game> getGameById(Long gameId){
        return gameRepository.findById(gameId);
    }

    public List<PlayerDTO> getPlayersInGame(Long gameId){
        String playerServiceUrl = "http://localhost:8082/players?gameId=" + gameId;
        ResponseEntity<PlayerDTO[]> response = restTemplate.getForEntity(playerServiceUrl, PlayerDTO[].class);
        return List.of(response.getBody());
    }

    public Boolean canAddPlayer(Long gameId){
        Optional<Game> game = gameRepository.findById(gameId);
        String playerServiceUrl = "http://localhost:8082/players?gameId=" + gameId;
        ResponseEntity<PlayerDTO[]> response = restTemplate.getForEntity(playerServiceUrl, PlayerDTO[].class);
        PlayerDTO[] players = response.getBody();

        return players == null || players.length < 2;
    }
}
