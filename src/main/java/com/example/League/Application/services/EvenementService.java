package com.example.League.Application.services;

import com.example.League.Application.models.Evenement;
import java.util.List;

public interface EvenementService {
    Evenement saveEvenement(Evenement evenement);
    List<Evenement> getEvenementsByGameId(Long gameId);
}
