package com.example.League.Application.controllers;

import com.example.League.Application.models.Commentaire;
import com.example.League.Application.models.Game;
import com.example.League.Application.models.Utilisateur;
import com.example.League.Application.services.CommentaireService;
import com.example.League.Application.services.GameService;
import com.example.League.Application.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("commentaire")
public class CommentaireController {
    @Autowired
    CommentaireService commentaireService;

    @Autowired
    GameService gameService;

    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<?> addCommentaire(@RequestBody Commentaire commentaire) {
        Optional<Game> game = gameService.getGameById(commentaire.getGame().getId());
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(commentaire.getJournaliste().getId());

        if (game.isEmpty() || utilisateur.isEmpty() || utilisateur.get().getRole() != Utilisateur.Role.JOURNALISTE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Seuls les journalistes sont autorisés à enregistrer des événements");
        }

        commentaire.setTimestamp(LocalDateTime.now());
        commentaireService.saveCommentaire(commentaire);
        return ResponseEntity.status(HttpStatus.CREATED).body("Commentaire ajouté avec succès");
    }

    @GetMapping("/{Id}")
    public ResponseEntity<?> getCommentairesByGame(@PathVariable("Id") Long gameId) {
        List<Commentaire> commentaires = commentaireService.getCommentairesByGameId(gameId);
        return new ResponseEntity<>(commentaires, HttpStatus.OK);
    }
}
