package com.example.League.Application.repositories;

import com.example.League.Application.models.Game;
import com.example.League.Application.models.SuspensionMatch;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspensionMatchRepository extends JpaRepository<SuspensionMatch, Long> {
    SuspensionMatch save(SuspensionMatch suspensionMatch);
    Optional<Game> getSuspensionById(Long id);
}
