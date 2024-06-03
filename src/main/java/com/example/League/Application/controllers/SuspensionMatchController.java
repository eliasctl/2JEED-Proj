package com.example.League.Application.controllers;

import com.example.League.Application.models.SuspensionMatch;
import com.example.League.Application.services.SuspensionMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/suspension-match")
public class SuspensionMatchController {
    @Autowired
    SuspensionMatchService suspensionMatchService;

    @PostMapping
    public ResponseEntity<?> suspendMatch(@RequestBody SuspensionMatch suspensionMatch) {
        SuspensionMatch savedSuspensionMatch = suspensionMatchService.saveSuspensionMatch(suspensionMatch);
        return new ResponseEntity<>(savedSuspensionMatch, HttpStatus.CREATED);
    }
}
