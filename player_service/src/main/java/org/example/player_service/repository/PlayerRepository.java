package org.example.player_service.repository;

import org.example.player_service.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
    List<Player> findByGameId(Long gameId);
}
