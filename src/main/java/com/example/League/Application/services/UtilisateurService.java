package com.example.League.Application.services;

import com.example.League.Application.models.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
    Utilisateur saveUtilisateur(Utilisateur utilisateur);
    List<Utilisateur> getAllUtilisateurs();
    Optional<Utilisateur> getUtilisateurById(Long id);
    Optional<Utilisateur> getUtilisateurByEmail(String email);
}
