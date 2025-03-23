package org.example.shot_service.repository;

import org.example.shot_service.model.Shot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShotRepository extends JpaRepository<Shot, Long> {
    List<Shot> findByPlayerId(Long playerId);
}
