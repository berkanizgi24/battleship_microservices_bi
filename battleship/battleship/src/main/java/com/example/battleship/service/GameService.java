package com.example.battleship.service;

import com.example.battleship.model.Game;
import com.example.battleship.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game createGame(){
        Game game = new Game();
        return gameRepository.save(game);
    }
}
