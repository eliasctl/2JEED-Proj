package com.example.League.Application.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class SuspensionMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String raison;
    private LocalDateTime dateSuspension;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur memberLeague;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Game match;

    public Utilisateur getMemberLeague() {
        return memberLeague;
    }

    public void setMemberLeague(Utilisateur memberLeague) {
        this.memberLeague = memberLeague;
    }

    public void setdateSuspension(LocalDateTime dateSuspension) {
        this.dateSuspension = dateSuspension;
    }

    public Object getStatus() {
        throw new UnsupportedOperationException("Unimplemented method 'getStatus'");
    }
}
