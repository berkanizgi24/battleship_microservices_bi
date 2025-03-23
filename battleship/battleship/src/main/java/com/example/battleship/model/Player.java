package com.example.battleship.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JsonIgnore
    private Game game;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Ship> ships;

    public Player() {}

    public Player(String name){
        this.name = name;
    }

    public Player(String name, Game game){
        this.name = name;
        this.game = game;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Ship> getShips() {
        return ships;
    }
    public void setShips(List<Ship> ship) {
        this.ships = ship;
    }


}
