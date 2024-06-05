package com.example.League.Application.controllers;

import com.example.League.Application.models.Evenement;
import com.example.League.Application.models.Game;
import com.example.League.Application.models.Utilisateur;
import com.example.League.Application.services.EvenementService;
import com.example.League.Application.services.GameService;
import com.example.League.Application.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("evenement")
public class EvenementController {
    @Autowired
    EvenementService evenementService;

    @Autowired
    GameService gameService;

    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<?> addEvenement(@RequestBody Evenement evenement) {
        // Récupérer l'utilisateur par son ID
        Optional<Utilisateur> optUtilisateur = utilisateurService.getUtilisateurById(evenement.getJournaliste().getId());
        if (optUtilisateur.isEmpty() || !isJournaliste(optUtilisateur.get())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Seuls les journalistes sont autorisés à enregistrer des événements");
        }

        // Vérifie si le type d'événement est valide
        if (!isValidEventType(evenement.getType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Type d'événement invalide");
        }

        Optional<Game> game = gameService.getGameById(evenement.getGame().getId());
        if (game.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        evenement.setTimestamp(LocalDateTime.now());
        evenementService.saveEvenement(evenement);
        return new ResponseEntity<>(
                "Événement ajouté avec succès",
                HttpStatus.CREATED
        );
    }

    // Méthode pour vérifier si l'utilisateur est un journaliste
    private boolean isJournaliste(Utilisateur utilisateur) {
        return utilisateur != null && Utilisateur.Role.JOURNALISTE.equals(utilisateur.getRole());
    }

    // Méthode pour vérifier si le type d'événement est valide
    private boolean isValidEventType(String type) {
        return type != null && (type.equals("But") || type.equals("Carton jaune") || type.equals("Carton rouge"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEvenementById(@PathVariable Long id) {
        Optional<Evenement> evenement = evenementService.getEvenementById(id);
        if (evenement.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evenement.get());
    }
}
