package com.example.League.Application.controllers;

import com.example.League.Application.models.Journee;
import com.example.League.Application.models.Saison;
import com.example.League.Application.services.JourneeService;
import com.example.League.Application.services.SaisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("journee")
public class JourneeController {
    @Autowired
    JourneeService journeeService;
    @Autowired
    SaisonService saisonService;

    @PostMapping
    public ResponseEntity <?> createJournee(@RequestBody Journee entity){
        Optional<Saison> saison = saisonService.getSaisonById(entity.getSaison().getId());
        if (saison.isEmpty())
            return ResponseEntity.notFound().build();

        String libelle = entity.getLibelle();
        for (Journee journee : saison.get().getJournees()){
            if (journee.getLibelle().equals(libelle))
                return new ResponseEntity<>(
                        "journée exsite déjà pour cette saison",
                        HttpStatus.BAD_REQUEST
                );
        }
        return new ResponseEntity<>(
                "journé ajouté avec succès",
                HttpStatus.CREATED
        );
    }
}
