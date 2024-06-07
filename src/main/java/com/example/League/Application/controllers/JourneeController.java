package com.example.League.Application.controllers;

import com.example.League.Application.models.Journee;
import com.example.League.Application.models.Saison;
import com.example.League.Application.services.JourneeService;
import com.example.League.Application.services.SaisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("journee")
public class JourneeController {
    @Autowired
    JourneeService journeeService;
    @Autowired
    SaisonService saisonService;

    @PostMapping
    public ResponseEntity<?> createJournee(@RequestBody Journee entity) {
        Optional<Saison> saison = saisonService.getSaisonById(entity.getSaison().getId());
        if (saison.isEmpty())
            return ResponseEntity.notFound().build();

        String libelle = entity.getLibelle();
        if (journeeService.existsByLibelleAndSaison(libelle, saison.get())) {
            return new ResponseEntity<>(
                    "Une journée avec le même libellé existe déjà pour cette saison",
                    HttpStatus.BAD_REQUEST);
        }

        journeeService.createJournee(entity);
        return new ResponseEntity<>(
                "Journée ajoutée avec succès",
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJourneeById(@PathVariable Long id) {
        Optional<Journee> journee = journeeService.getJourneeById(id);
        if (journee.isEmpty())
            return ResponseEntity.notFound().build();
        return new ResponseEntity<>(journee.get(), HttpStatus.OK);
    }
}
