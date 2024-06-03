package com.example.League.Application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String reportReason;
    private String suspendReason;

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    @ManyToOne
    @JoinColumn(name = "journée_id", nullable = false)
    private Journee journee;

    public void setSuspendReason(String suspendReason) {
        this.suspendReason = suspendReason;
    }
}
