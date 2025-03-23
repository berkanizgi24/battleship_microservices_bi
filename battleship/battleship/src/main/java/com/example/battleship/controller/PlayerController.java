package com.example.battleship.controller;

import com.example.battleship.model.Player;
import com.example.battleship.service.PlayerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public Player addPlayer(@RequestParam Long gameId, @RequestParam String name) {
        return playerService.addPlayer(gameId, name);
    }
}
