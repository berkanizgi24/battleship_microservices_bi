package com.example.dashboard.controller;

import com.example.dashboard.dto.GameDetailsDTO;
import com.example.dashboard.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final DashboardService dashboardService;

    public GatewayController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/gameDetails/{gameId}")
    public ResponseEntity<GameDetailsDTO> getGameDetails(@PathVariable long gameId) {
        return ResponseEntity.ok(dashboardService.getGameDetails(gameId));
    }
}
