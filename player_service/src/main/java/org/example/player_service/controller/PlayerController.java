package org.example.player_service.controller;

import org.example.player_service.model.Player;
import org.example.player_service.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getPlayers(@RequestParam Long gameId){
        return playerService.getPlayersByGameId(gameId);
    }

    @PostMapping
    public Player createPlayer(@RequestParam Long gameId, @RequestParam String name){
        return playerService.createPlayer(gameId, name);
    }
}