package com.example.League.Application.controllers;

import com.example.League.Application.models.Evenement;
import com.example.League.Application.models.Game;
import com.example.League.Application.services.EvenementService;
import com.example.League.Application.services.GameService;
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

    @PostMapping
    public ResponseEntity<?> addEvenement(@RequestBody Evenement evenement) {
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
}
