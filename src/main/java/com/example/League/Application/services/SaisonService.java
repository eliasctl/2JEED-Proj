package com.example.League.Application.services;

import com.example.League.Application.models.Saison;

import java.util.Optional;

public interface SaisonService {
    Saison createSaison(Saison saison);
    void deleteSaison(Long id);
    Optional<Saison> getSaisonById(Long id);
}
