package org.example.player_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long gameId;

    public Player() {}

    public Player(String name, Long gameId) {
        this.name = name;
        this.gameId = gameId;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGameId(){
        return gameId;
    }

    public void setGameId(Long gameId){
        this.gameId = gameId;
    }

}
