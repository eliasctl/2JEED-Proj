package com.example.League.Application.services;

import com.example.League.Application.models.Evenement;
import java.util.List;
import java.util.Optional;

public interface EvenementService {
    Evenement saveEvenement(Evenement evenement);
    List<Evenement> getEvenementsByGameId(Long gameId);
    Optional<Evenement> getEvenementById(Long id);
}
