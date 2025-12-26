package com.pc.eliminationservice.dto.request;

import com.pc.eliminationservice.model.TreatmentPlan;
import com.pc.eliminationservice.model.enums.ScheduleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRuleRequest {
    private ScheduleType scheduleType;

    private Integer frequencyInDays;

    private Integer repeatCount;

    private Integer customOccurrencePerPeriod;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime reminderTime;

    private Integer reminderWindowDays;

    private TreatmentPlan treatmentPlan;
}
