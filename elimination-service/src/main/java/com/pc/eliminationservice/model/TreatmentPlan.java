package com.pc.eliminationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "treatment_plan")
public class TreatmentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(name = "migrant_health_id", nullable = false)
    private UUID migrantHealthId;

    @Column(nullable = false)
    private String diseaseCode; // it denotes what treatment it is

    private LocalDate startDate;

    private LocalDate endDate;


}
