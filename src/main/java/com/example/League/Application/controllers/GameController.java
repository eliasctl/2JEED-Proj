package com.example.League.Application.controllers;

import com.example.League.Application.models.Game;
import com.example.League.Application.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        return ResponseEntity.ok(gameService.createGame(game));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Game updatedGame = gameService.updateGame(id, game);
        if (updatedGame != null) {
            return ResponseEntity.ok(updatedGame);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Game> startGame(@PathVariable Long id) {
        Optional<Game> game = gameService.startGame(id);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/end")
    public ResponseEntity<Game> endGame(@PathVariable Long id) {
        Optional<Game> game = gameService.endGame(id);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/report")
    public ResponseEntity<Game> reportGame(@PathVariable Long id, @RequestParam String reason) {
        Optional<Game> game = gameService.reportGame(id, reason);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/suspend")
    public ResponseEntity<Game> suspendGame(@PathVariable Long id, @RequestParam String reason) {
        Optional<Game> game = gameService.suspendGame(id, reason);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
