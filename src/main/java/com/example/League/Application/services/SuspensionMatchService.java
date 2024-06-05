package com.example.League.Application.services;

import com.example.League.Application.models.SuspensionMatch;
import java.util.Optional;

public interface SuspensionMatchService {
    SuspensionMatch saveSuspensionMatch(SuspensionMatch suspensionMatch);
    Optional<SuspensionMatch> getSuspensionById(Long id);
}
