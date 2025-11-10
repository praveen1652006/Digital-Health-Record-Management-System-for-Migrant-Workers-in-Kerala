package com.pc.eliminationservice.dto.request;

import com.pc.eliminationservice.model.Medication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentPlanRequest {

    private String diseaseCode;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<Medication> medicationList;

    private LocalTime reminderTime;

    private Integer frequencyInDays;

    private String preferredChannel;

    private String preferredLanguage;
}
