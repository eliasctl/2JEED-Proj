package com.example.League.Application.services;

import com.example.League.Application.models.Report;
import java.util.Optional;

public interface ReportService {
    Report saveReport(Report report);
    Optional<Report> getReportById(Long id);
}
