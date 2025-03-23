package com.example.battleship.service;

import com.example.battleship.model.Player;
import com.example.battleship.model.Ship;
import com.example.battleship.repository.PlayerRepository;
import com.example.battleship.repository.ShipRepository;
import org.springframework.stereotype.Service;

@Service
public class ShipService
{
    private final ShipRepository shipRepository;
    private final PlayerRepository playerRepository;

    public ShipService(ShipRepository shipRepository, PlayerRepository playerRepository){
        this.shipRepository = shipRepository;
        this.playerRepository = playerRepository;
    }

    public Ship placeShip(Long playerId, int x, int y, int length, boolean isHorizontal){
        Player player = playerRepository.findById(playerId).orElse(null);
        if(player == null){
            return null;
        }
        Ship ship = new Ship();
        ship.setX(x);
        ship.setY(y);
        ship.setLength(length);
        ship.setHorizontal(isHorizontal);
        ship.setPlayer(player);
        return shipRepository.save(ship);
    }
}
