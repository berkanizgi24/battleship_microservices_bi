package com.example.dashboard.controller;

import com.example.dashboard.dto.GameDTO;
import com.example.dashboard.dto.PlayerDTO;
import com.example.dashboard.dto.ShipDTO;
import com.example.dashboard.dto.ShotDTO;
import com.example.dashboard.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @Tag(name = "Game Controller")
    @Operation(summary = "Alle Spiele aufgelistet")
    @GetMapping("/games")
    public ResponseEntity<List<GameDTO>> getAllGames() {
        return ResponseEntity.ok(dashboardService.getAllGames());
    }


    @Tag(name = "Game Controller")
    @Operation(summary = "Erstellt neues Spiel")
    @PostMapping("/games")
    public ResponseEntity<GameDTO> createGame() {
        return ResponseEntity.ok(dashboardService.createGame());
    }

    @Tag(name = "Game Controller")
    @Operation(summary = "Liefert Spieler eines bestimmten Spiels")
    @GetMapping("/games/{gameId}/players")
    public ResponseEntity<List<PlayerDTO>> getPlayersInGame(@PathVariable Long gameId) {
        return ResponseEntity.ok(dashboardService.getPlayersInGame(gameId));
    }

    @Tag(name = "Player Controller")
    @Operation(summary = "Liefert alle Spieler")
    @GetMapping("/players")
    public ResponseEntity<List<PlayerDTO>> getPlayers(@RequestParam Long gameId) {
        return ResponseEntity.ok(dashboardService.getPlayers(gameId));
    }

    @Tag(name = "Player Controller")
    @Operation(summary = "Erstellt neuen Spieler im Game (max 2)")
    @PostMapping("/players")
    public ResponseEntity<PlayerDTO> createPlayer(@RequestParam Long gameId, @RequestParam String name) {
        return ResponseEntity.ok(dashboardService.createPlayer(gameId, name));
    }
    @Tag(name = "Ship Controller")
    @Operation(summary = "Liefert alle Schiffe")
    @GetMapping("/ships/{playerId}")
    public ResponseEntity<List<ShipDTO>> getShips(@PathVariable Long playerId) {
        return ResponseEntity.ok(dashboardService.getShips(playerId));
    }

    @Tag(name = "Ship Controller")
    @Operation(summary = "Setzt ein Schiff eines Spielers")
    @PostMapping("/ships")
    public ResponseEntity<ShipDTO> placeShip(
            @RequestParam Long playerId,
            @RequestParam int x,
            @RequestParam int y,
            @RequestParam int length,
            @RequestParam boolean isHorizontal) {
        return ResponseEntity.ok(dashboardService.placeShip(playerId, x, y, length, isHorizontal));
    }

    @Tag(name = "Shot Controller")
    @Operation(summary = "Schießt auf ein Schiff")
    @PostMapping("/shots")
    public ResponseEntity<ShotDTO> placeShot(
            @RequestParam Long attackerId,
            @RequestParam Long defenderId,
            @RequestParam int x,
            @RequestParam int y) {
        return ResponseEntity.ok(dashboardService.placeShot(attackerId, defenderId, x, y));
    }

    @Tag(name = "Game Controller")
    @Operation(summary = "Löscht ein Spiel")
    @DeleteMapping("/games/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId) {
        dashboardService.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "Player Controller")
    @Operation(summary = "Löscht einen Spieler")
    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long playerId) {
        dashboardService.deletePlayer(playerId);
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "Ship Controller")
    @Operation(summary = "Löscht ein Schiff")
    @DeleteMapping("/ships/{shipId}")
    public ResponseEntity<Void> deleteShip(@PathVariable Long shipId) {
        dashboardService.deleteShip(shipId);
        return ResponseEntity.noContent().build();


    }

    @Tag(name = "Shot Controller")
    @Operation(summary = "Löscht einen Schuss")
    @DeleteMapping("/shots/{shotId}")
    public ResponseEntity<Void> deleteShot(@PathVariable Long shotId) {
        dashboardService.deleteShot(shotId);
        return ResponseEntity.noContent().build();
    }
}
