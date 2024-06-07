package com.example.League.Application.controllers;

import com.example.League.Application.models.Report;
import com.example.League.Application.models.Game;
import com.example.League.Application.models.Utilisateur;
import com.example.League.Application.services.ReportService;
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
@RequestMapping("report")
public class ReportController {
    @Autowired
    ReportService reportService;
    @Autowired
    GameService matchService;
    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<?> addReport(@RequestBody Report report) {
        Optional<Utilisateur> optUtilisateur = utilisateurService.getUtilisateurById(report.getMemberLeague().getId());

        if (optUtilisateur.isEmpty() || !isMemberLeague(optUtilisateur.get())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Seuls les membres de la ligue sont autorisés à reporter un match");
        }

        Optional<Game> match = matchService.getGameById(report.getMatch().getId());

        if (match.isPresent() && !"En attente".equals(match.get().getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Le match a déjà commencé");
        }

        report.setTimestamp(LocalDateTime.now());
        reportService.saveReport(report);
        return new ResponseEntity<>(
                "Report ajouté avec succès",
                HttpStatus.CREATED);
    }

    private boolean isMemberLeague(Utilisateur utilisateur) {
        return utilisateur != null && Utilisateur.Role.MEMBER_LEAGUE.equals(utilisateur.getRole());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReportById(@PathVariable Long id) {
        Optional<Report> report = reportService.getReportById(id);
        if (report.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report.get());
    }
}
