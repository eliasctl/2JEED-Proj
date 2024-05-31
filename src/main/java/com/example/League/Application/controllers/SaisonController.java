package com.example.League.Application.controllers;

import com.example.League.Application.models.Saison;
import com.example.League.Application.services.SaisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("saison")
public class SaisonController {
    @Autowired
    SaisonService saisonService;

    @PostMapping
    public ResponseEntity<?> createSaison(@RequestBody Saison saison){
        return new ResponseEntity<>(
               saisonService.createSaison(saison),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSaison(@PathVariable Long id){
        Optional<Saison> saison = saisonService.getSaisonById(id);
        if (saison.isEmpty())
            return ResponseEntity.notFound().build();

        if (saison.get().getJournees().size() != 0)
            return new ResponseEntity<>(
                    "Vous n'avez pas le droit",
                    HttpStatus.FORBIDDEN
            );
        saisonService.deleteSaison(id);
        return new ResponseEntity<>(
                HttpStatus.GONE
        );
    }
}
