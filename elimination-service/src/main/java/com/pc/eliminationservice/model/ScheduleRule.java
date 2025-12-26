package com.pc.eliminationservice.model;

import com.pc.eliminationservice.model.enums.ScheduleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "schedule_rule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ruleId;

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    private Integer frequencyInDays;

    private Integer repeatCount;

    private Integer customOccurrencePerPeriod;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime reminderTime;

    private Integer reminderWindowDays;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private TreatmentPlan treatmentPlan;

    @OneToMany(mappedBy = "scheduleRule", cascade = CascadeType.ALL)
    private List<ScheduledOccurrence> occurrences;

}
