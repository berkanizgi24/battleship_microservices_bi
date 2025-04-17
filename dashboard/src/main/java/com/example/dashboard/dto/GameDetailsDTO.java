package com.example.dashboard.dto;

import java.util.List;

public record GameDetailsDTO(
        GameDTO game,
        List<PlayerDTO> players,
        List<ShipDTO> ships
) {}
