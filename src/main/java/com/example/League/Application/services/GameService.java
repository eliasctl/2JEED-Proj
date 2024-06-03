package com.example.League.Application.services;

import com.example.League.Application.models.Game;
import java.util.List;
import java.util.Optional;

public interface GameService {
    Game createGame(Game game);
    Game updateGame(Long id, Game game);
    void deleteGame(Long id);
    Optional<Game> getGameById(Long id);
    List<Game> getAllGames();
    Optional<Game> startGame(Long id);
    Optional<Game> endGame(Long id);
    Optional<Game> reportGame(Long id, String reason);
    Optional<Game> suspendGame(Long id, String reason);
}
