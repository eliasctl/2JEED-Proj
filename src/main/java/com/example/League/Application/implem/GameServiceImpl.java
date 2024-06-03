package com.example.League.Application.implem;

import com.example.League.Application.models.Game;
import com.example.League.Application.repositories.GameRepository;
import com.example.League.Application.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(Long id, Game game) {
        Optional<Game> existingGame = gameRepository.findById(id);
        if (existingGame.isPresent()) {
            Game updatedGame = existingGame.get();
            updatedGame.setName(game.getName());
            return gameRepository.save(updatedGame);
        }
        return null;
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> startGame(Long id) {
        return gameRepository.findById(id).map(game -> {
            game.setStartTime(LocalDateTime.now());
            game.setStatus("In Progress");
            return gameRepository.save(game);
        });
    }

    @Override
    public Optional<Game> endGame(Long id) {
        return gameRepository.findById(id).map(game -> {
            game.setEndTime(LocalDateTime.now());
            game.setStatus("Completed");
            return gameRepository.save(game);
        });
    }

    @Override
    public Optional<Game> reportGame(Long id, String reason) {
        return gameRepository.findById(id).map(game -> {
            if ("Scheduled".equals(game.getStatus())) {
                game.setStatus("Reported");
                game.setReportReason(reason);
                return gameRepository.save(game);
            }
            return null;
        });
    }

    @Override
    public Optional<Game> suspendGame(Long id, String reason) {
        return gameRepository.findById(id).map(game -> {
            if ("In Progress".equals(game.getStatus())) {
                game.setStatus("Suspended");
                game.setSuspendReason(reason);
                return gameRepository.save(game);
            }
            return null;
        });
    }
}
