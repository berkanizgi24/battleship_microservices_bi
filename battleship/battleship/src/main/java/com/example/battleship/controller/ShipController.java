package com.example.battleship.controller;

import com.example.battleship.model.Ship;
import com.example.battleship.service.ShipService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ships")
public class ShipController {
    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @PostMapping
    public Ship placeShip(@RequestParam Long playerId, @RequestParam int x, @RequestParam int y, @RequestParam int length, @RequestParam boolean isHorizontal){
        return shipService.placeShip(playerId, x, y, length, isHorizontal);
    }
}
