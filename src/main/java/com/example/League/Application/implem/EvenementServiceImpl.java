package com.example.League.Application.implem;

import com.example.League.Application.models.Evenement;
import com.example.League.Application.repositories.EvenementRepository;
import com.example.League.Application.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementServiceImpl implements EvenementService {
    @Autowired
    private EvenementRepository evenementRepository;

    @Override
    public Evenement saveEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    @Override
    public List<Evenement> getEvenementsByGameId(Long gameId) {
        return evenementRepository.findByGameId(gameId);
    }

    @Override
    public Optional<Evenement> getEvenementById(Long id) {
        return evenementRepository.findById(id);
    }
}
