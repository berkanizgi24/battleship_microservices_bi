package com.example.battleship.controller;

import com.example.battleship.model.Shot;
import com.example.battleship.service.ShotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shots")
public class ShotController {
    private final ShotService shotService;

    public ShotController(ShotService shotService) {
        this.shotService = shotService;
    }

    @PostMapping
    public ResponseEntity<String> fireShot(@RequestParam Long playerId, @RequestParam int x, @RequestParam int y) {
        Shot shot = shotService.fireShot(playerId, x,y);
        if (shot == null) {
            return ResponseEntity.badRequest().body("Player not found :(");
        }
        if (shot.isHit()){
            return ResponseEntity.ok("Nice shot buddy");
        } else {
            return ResponseEntity.ok("No hit buddy");
        }
    }
}
