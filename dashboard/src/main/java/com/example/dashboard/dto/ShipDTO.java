package com.example.dashboard.dto;

public record ShipDTO(Long id,
                      Long playerId,
                      int x,
                      int y,
                      int length,
                      boolean isHorizontal) {
}
