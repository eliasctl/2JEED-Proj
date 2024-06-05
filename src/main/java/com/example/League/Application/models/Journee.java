package com.example.League.Application.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Journee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String libelle;

    @ManyToOne
    @JoinColumn(name = "saison_id")
    private Saison saison;

    public Long getSaison_id() {
        return saison != null ? saison.getId() : null;

    }
}
