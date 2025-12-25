package com.pc.eliminationservice.model;

import com.pc.eliminationservice.model.enums.OccurrenceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "scheduled_occurrence")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledOccurrence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long occurrenceId;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private TreatmentPlan treatmentPlan;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private ScheduleRule scheduleRule;

    private LocalDate dueDate;

    private LocalDate remainderWindowStart;
    private LocalDate remainderWindowEnd;

    @Enumerated(EnumType.STRING)
    private OccurrenceStatus status;
}
