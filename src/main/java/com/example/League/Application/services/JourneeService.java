package com.example.League.Application.services;

import com.example.League.Application.models.Journee;

import java.util.Optional;

public interface JourneeService {
    Journee createJournee (Journee journee);
    Optional<Journee> getJourneeByLibelle (String libelle);
}
