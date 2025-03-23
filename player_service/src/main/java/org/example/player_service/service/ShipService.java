package org.example.player_service.service;

import org.example.player_service.model.Player;
import org.example.player_service.repository.PlayerRepository;
import org.example.player_service.repository.ShipRepository;
import org.springframework.stereotype.Service;
import org.example.player_service.model.Ship;

import java.util.List;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final PlayerRepository playerRepository;

    public ShipService(ShipRepository shipRepository, PlayerRepository playerRepository){
        this.shipRepository = shipRepository;
        this.playerRepository = playerRepository;
    }

    public List<Ship> getShipsByPlayerId(Long playerId){
        return shipRepository.findByPlayerId(playerId);
    }

    public Ship placeShip(Long playerId, int x, int y, int length, boolean isHorizontal){
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player == null){
            throw new IllegalArgumentException("Player mit der Id: " + playerId + "nicht gefunden ://");
        }
        Ship ship = new Ship(x,y,length,isHorizontal);
        ship.setPlayerId(playerId);
        return shipRepository.save(ship);
    }



}
