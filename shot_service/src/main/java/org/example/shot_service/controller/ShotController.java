package org.example.shot_service.controller;

import org.example.shot_service.model.Shot;
import org.example.shot_service.service.ShotService;
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
    public Shot placeShot(@RequestParam Long attackerId, @RequestParam Long defenderId,@RequestParam int x, @RequestParam int y) {
        return shotService.placeShot(attackerId,defenderId, x, y);
    }
}
