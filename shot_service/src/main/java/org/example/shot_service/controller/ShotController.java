package org.example.shot_service.controller;

import org.example.shot_service.model.Shot;
import org.example.shot_service.service.ShotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shots")
public class ShotController {
    private final ShotService shotService;

    public ShotController(ShotService shotService) {
        this.shotService = shotService;
    }

    @PostMapping
    public Shot placeShot(@RequestParam Long attackerId, @RequestParam Long defenderId,@RequestParam int x, @RequestParam int y) {
        return shotService.placeShot(attackerId,defenderId, x, y);
    }

    @DeleteMapping("/{shotId}")
    public ResponseEntity<Void> deleteShot(Long shotId){
        shotService.deleteShot(shotId);
        return ResponseEntity.noContent().build();
    }
}
