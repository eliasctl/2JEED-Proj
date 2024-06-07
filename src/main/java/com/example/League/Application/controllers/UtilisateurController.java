package com.example.League.Application.controllers;

import com.example.League.Application.models.Utilisateur;
import com.example.League.Application.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<?> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> existingUtilisateur = utilisateurService.getUtilisateurByEmail(utilisateur.getEmail());
        if (existingUtilisateur.isPresent())
            return new ResponseEntity<>(
                    "Email déjà utilisé",
                    HttpStatus.BAD_REQUEST);

        utilisateurService.saveUtilisateur(utilisateur);
        return new ResponseEntity<>(
                "Utilisateur ajouté avec succès",
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUtilisateurById(@PathVariable Long id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        if (utilisateur.isEmpty())
            return ResponseEntity.notFound().build();

        return new ResponseEntity<>(utilisateur.get(), HttpStatus.OK);
    }
}
