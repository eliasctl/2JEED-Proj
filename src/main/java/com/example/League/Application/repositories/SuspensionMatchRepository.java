package com.example.League.Application.repositories;

import com.example.League.Application.models.SuspensionMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspensionMatchRepository extends JpaRepository<SuspensionMatch, Long> {
    SuspensionMatch save(SuspensionMatch suspensionMatch);
}
