package com.example.battleship.service;

import com.example.battleship.model.Game;
import com.example.battleship.model.Player;
import com.example.battleship.model.Ship;
import com.example.battleship.model.Shot;
import com.example.battleship.repository.PlayerRepository;
import com.example.battleship.repository.ShotRepository;
import org.springframework.stereotype.Service;

@Service
public class ShotService {
    private final ShotRepository shotRepository;
    private final PlayerRepository playerRepository;

    public ShotService(ShotRepository shotRepository, PlayerRepository playerRepository) {
        this.shotRepository = shotRepository;
        this.playerRepository = playerRepository;
    }

    public Shot fireShot(Long playerId, int x, int y) {
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player == null) {
            return null;
        }
        Game game = player.getGame();
        Player opponent = game.getPlayers().stream()
                .filter(p -> !p.getId().equals(playerId))
                .findFirst()
                .orElse(null);
        if (opponent == null) {
            return null;
        }
        boolean hit = false;
        for (Ship ship : opponent.getShips()) {
            if (ship.getX() <= x && x < ship.getX() + ship.getLength() && ship.getY() == y) {
                hit = true;
                break;
            }
        }
        Shot shot = new Shot(player, x, y, hit);
        return shotRepository.save(shot);
    }
}