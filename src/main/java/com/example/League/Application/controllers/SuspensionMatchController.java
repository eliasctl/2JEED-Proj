package com.example.League.Application.controllers;
import com.example.League.Application.models.Game;
import com.example.League.Application.models.SuspensionMatch;
import com.example.League.Application.models.Utilisateur;
import com.example.League.Application.services.GameService;
import com.example.League.Application.services.SuspensionMatchService;
import com.example.League.Application.services.UtilisateurService;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/suspension")
public class SuspensionMatchController {
    @Autowired
    SuspensionMatchService suspensionMatchService;
    @Autowired
    GameService matchService;
    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<?> suspendMatch(@RequestBody SuspensionMatch suspensionMatch) {
        Optional<Utilisateur> optUtilisateur = utilisateurService.getUtilisateurById(suspensionMatch.getMemberLeague().getId());

        if (optUtilisateur.isEmpty() || !isMemberLeague(optUtilisateur.get())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Seuls les membres de la ligue sont autorisés à suspendre un match");
        }

        Optional<Game> match = matchService.getGameById(suspensionMatch.getMatch().getId());

        if (match.isPresent() && !"En attente".equals(match.get().getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Le match a déjà commencé");
        }

        suspensionMatch.setdateSuspension(LocalDateTime.now());
        suspensionMatchService.saveSuspensionMatch(suspensionMatch);
        return new ResponseEntity<>(
                "La suspension a été faite avec succès",
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSuspensionById(@PathVariable Long id) {
        Optional<SuspensionMatch> suspensionMatch = suspensionMatchService.getSuspensionById(id);
        if (suspensionMatch.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(suspensionMatch.get());
    }

    private boolean isMemberLeague(Utilisateur utilisateur) {
        return utilisateur != null && Utilisateur.Role.MEMBER_LEAGUE.equals(utilisateur.getRole());
    }

    
}
