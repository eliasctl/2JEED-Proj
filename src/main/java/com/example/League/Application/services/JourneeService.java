package com.example.League.Application.services;

import com.example.League.Application.models.Journee;
import com.example.League.Application.models.Saison;

import java.util.Optional;

public interface JourneeService {
    Journee createJournee (Journee journee);
    Optional<Journee> getJourneeByLibelle (String libelle);
    Optional<Journee> getJourneeById(Long id);
    boolean existsByLibelleAndSaison(String libelle, Saison saison);
}
