package com.example.League.Application.implem;

import com.example.League.Application.models.Journee;
import com.example.League.Application.repositories.JourneeRepository;
import com.example.League.Application.services.JourneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JourneeImplem implements JourneeService {
    @Autowired
    JourneeRepository journeeRepository;

    @Override
    public Journee createJournee(Journee journee) {
        return journeeRepository.save(journee);
    }

    @Override
    public Optional<Journee> getJourneeByLibelle(String libelle) {
        return journeeRepository.findByLibelle(libelle);
    }
}
