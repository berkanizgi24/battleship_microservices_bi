package com.example.battleship.controller;

import com.example.battleship.model.Game;
import com.example.battleship.repository.GameRepository;
import com.example.battleship.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final GameRepository gameRepository;

    public GameController(GameService gameService, GameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @PostMapping
    public Game createGame(){
        return gameService.createGame();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getGame(@PathVariable Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null) {
            return ResponseEntity.status(404).body("Game not found :(");
        }
        return ResponseEntity.ok(game);
    }
}
