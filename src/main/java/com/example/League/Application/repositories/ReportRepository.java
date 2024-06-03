package com.example.League.Application.repositories;

import com.example.League.Application.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Report save(Report report);
}
