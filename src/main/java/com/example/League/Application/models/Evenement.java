package com.example.League.Application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
    
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur journaliste;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
