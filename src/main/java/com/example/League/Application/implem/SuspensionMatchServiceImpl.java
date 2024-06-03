package com.example.League.Application.implem;

import com.example.League.Application.models.SuspensionMatch;
import com.example.League.Application.repositories.SuspensionMatchRepository;
import com.example.League.Application.services.SuspensionMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuspensionMatchServiceImpl implements SuspensionMatchService {
    @Autowired
    private SuspensionMatchRepository suspensionMatchRepository;

    @Override
    public SuspensionMatch saveSuspensionMatch(SuspensionMatch suspensionMatch) {
        return suspensionMatchRepository.save(suspensionMatch);
    }
}
