package com.pc.eliminationservice.dto.response;

import com.pc.eliminationservice.model.Medication;
import com.pc.eliminationservice.model.enums.TreatmentStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentPlanResponse {
    private Long planId;

    private String diseaseCode;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<Medication> medicationList;

    private LocalTime reminderTime;

    private LocalDateTime nextActionDue;

    private TreatmentStatus status;

    private Integer frequencyInDays;

    private String preferredChannel;

    private String preferredLanguage;

    private LocalDateTime createdAt;
}
