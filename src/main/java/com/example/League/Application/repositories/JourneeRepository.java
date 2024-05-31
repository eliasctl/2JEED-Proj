package com.example.League.Application.repositories;

import com.example.League.Application.models.Journee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JourneeRepository extends JpaRepository<Journee, Long> {
    Journee save(Journee journee);
    Optional<Journee> findByLibelle (String libelle);
}
