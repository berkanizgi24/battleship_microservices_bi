package com.example.battleship.service;

import com.example.battleship.model.Game;
import com.example.battleship.model.Player;
import com.example.battleship.repository.GameRepository;
import com.example.battleship.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

        private final PlayerRepository playerRepository;
        private final GameRepository gameRepository;

        public PlayerService(PlayerRepository playerRepository, GameRepository gameRepository) {
            this.playerRepository = playerRepository;
            this.gameRepository = gameRepository;
        }



    public Player addPlayer(Long gameId, String playerName){
        Game game = gameRepository.findById(gameId).orElse(null);
        if(game == null){
            return null;
        }
        Player player = new Player();
        player.setName(playerName);
        player.setGame(game);
        return playerRepository.save(player);
    }
}
