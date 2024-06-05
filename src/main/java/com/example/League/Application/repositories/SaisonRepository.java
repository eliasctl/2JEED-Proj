package com.example.League.Application.repositories;

import com.example.League.Application.models.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaisonRepository extends JpaRepository<Saison, Long> {
    Saison save(Saison saison);
    Optional<Saison> findById(Long id);
    void deleteById(Long id);
    Optional<Saison> findByLibelle(String libelle);
}
