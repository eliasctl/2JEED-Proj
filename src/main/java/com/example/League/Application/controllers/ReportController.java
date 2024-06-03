package com.example.League.Application.controllers;

import com.example.League.Application.models.Report;
import com.example.League.Application.models.Game;
import com.example.League.Application.services.ReportService;
import com.example.League.Application.services.GameService;
import com.example.League.Application.services.SuspensionMatchService;
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
    SuspensionMatchService memberLeagueService;

    @PostMapping
    public ResponseEntity<?> addReport(@RequestBody Report report) {
        Optional<Game> match = matchService.getGameById(report.getMatch().getId());

        if (match.isPresent() && match.get().getStartTime() != null) {
            return new ResponseEntity<>(
                    "Le match a déjà commencé",
                    HttpStatus.BAD_REQUEST
            );
        }

        report.setTimestamp(LocalDateTime.now());
        reportService.saveReport(report);
        return new ResponseEntity<>(
                "Report ajouté avec succès",
                HttpStatus.CREATED
        );
    }
}
