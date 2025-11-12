package com.pc.eliminationservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pc.eliminationservice.model.enums.TreatmentLogStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "treatment_log")
public class TreatmentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private TreatmentPlan treatmentPlan;

    private LocalDate logDate;

    @Enumerated(EnumType.STRING)
    private TreatmentLogStatus treatmentLogStatus;

    private String notes;

    @CreatedDate
    private LocalDate createdAt;
}
