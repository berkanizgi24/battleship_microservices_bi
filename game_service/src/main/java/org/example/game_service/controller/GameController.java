package org.example.game_service.controller;

import org.example.game_service.dto.PlayerDTO;
import org.example.game_service.model.Game;
import org.example.game_service.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getGames(){
        return gameService.getAllGames();
    }

    @PostMapping
    public Game createGame(){
        return gameService.createGame();
    }

    @GetMapping("/{gameId}/doesGameExist")
    public ResponseEntity<Boolean> doesGameExist(@PathVariable Long gameId){
        Optional<Game> game = gameService.getGameById(gameId);
        return ResponseEntity.ok(game.isPresent());
    }

    @GetMapping("/{gameId}/players")
    public ResponseEntity<List<PlayerDTO>> getPlayersInGame(@PathVariable Long gameId){
        List<PlayerDTO> players = gameService.getPlayersInGame(gameId);
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{gameId}/canAddPlayer")
    public ResponseEntity<Boolean> canAddPlayer(@PathVariable Long gameId){
        boolean result = gameService.canAddPlayer(gameId);
        return ResponseEntity.ok(result);
    }

}
