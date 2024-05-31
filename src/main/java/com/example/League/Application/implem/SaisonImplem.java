package com.example.League.Application.implem;

import com.example.League.Application.models.Saison;
import com.example.League.Application.repositories.SaisonRepository;
import com.example.League.Application.services.SaisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaisonImplem implements SaisonService {
    @Autowired
    SaisonRepository saisonRepository;

    @Override
    public Saison createSaison(Saison saison) {
        return saisonRepository.save(saison);
    }

    @Override
    public void deleteSaison(Long id) {
        saisonRepository.deleteById(id);
    }

    @Override
    public Optional<Saison> getSaisonById(Long id) {
        return saisonRepository.findById(id);
    }
}
