package org.example.player_service.service;

import org.example.player_service.dto.GameDTO;
import org.example.player_service.model.Player;
import org.example.player_service.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final RestTemplate restTemplate;

    public PlayerService(PlayerRepository playerRepository, RestTemplate restTemplate) {
        this.playerRepository = playerRepository;
        this.restTemplate = restTemplate;
    }

    public List<Player> getPlayersByGameId(Long gameId){
        return playerRepository.findByGameId(gameId);
    }

    public Player createPlayer(Long gameId, String name){
        String gameServiceUrl = "http://localhost:8081/games/" + gameId + "/doesGameExist";
        try{
            Boolean gameExists = restTemplate.getForObject(gameServiceUrl, Boolean.class);

           if (Boolean.FALSE.equals(gameExists)){
         throw new IllegalArgumentException("Game mit Id:" + gameId + "existiert leider nicht :/");
            }
        } catch (Exception e){
            throw new IllegalArgumentException("Game mit Id" + gameId + "existiert nicht");
        }

        String checkUrl = "http://localhost:8081/games/" + gameId + "/canAddPlayer";
        Boolean canAddPlayer = restTemplate.getForObject(checkUrl, Boolean.class);

        if (Boolean.FALSE.equals(canAddPlayer)){
            throw new IllegalArgumentException("Dieses Spiel hat schon 2 Spieler :(");
        }

        Player player = new Player();
        player.setGameId(gameId);
        player.setName(name);
        return playerRepository.save(player);
    }
}
