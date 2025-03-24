package com.example.dashboard.dto;

public record ShotDTO(Long id,
                      int x,
                      int y,
                      boolean hit,
                      Long playerId
){
}
