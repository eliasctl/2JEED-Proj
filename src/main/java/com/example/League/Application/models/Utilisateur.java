package com.example.League.Application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    private String prenom;
    private String motDePasse;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        JOURNALISTE,
        MEMBER_LEAGUE
    }

    @OneToMany(mappedBy = "memberLeague", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SuspensionMatch> suspensions;
}
