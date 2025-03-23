package org.example.player_service.controller;

import org.example.player_service.model.Ship;
import org.example.player_service.service.ShipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ships")
public class ShipController {
    private final ShipService shipService;

    public ShipController(ShipService shipService){
        this.shipService = shipService;
    }

    @GetMapping("/{playerId}")
    public List<Ship> getShipsByPlayerId(@PathVariable Long playerId){
        return shipService.getShipsByPlayerId(playerId);
    }

    @PostMapping
    public Ship placeShip(@RequestParam Long playerId, @RequestParam int x, @RequestParam int y, @RequestParam int length, @RequestParam boolean isHorizontal){
        return shipService.placeShip(playerId,x,y,length,isHorizontal);
    }
}
