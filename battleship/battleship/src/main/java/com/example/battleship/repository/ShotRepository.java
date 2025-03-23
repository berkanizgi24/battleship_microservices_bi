package com.example.battleship.repository;

import com.example.battleship.model.Shot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShotRepository extends JpaRepository<Shot, Long>{
}
