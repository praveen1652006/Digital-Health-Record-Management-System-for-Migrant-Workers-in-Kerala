package com.pc.eliminationservice.mapper;

import com.pc.eliminationservice.dto.request.TreatmentPlanRequest;
import com.pc.eliminationservice.dto.response.TreatmentPlanResponse;
import com.pc.eliminationservice.model.TreatmentPlan;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TreatmentMapper {

    public static TreatmentPlan toTreatmentPlan(TreatmentPlanRequest treatmentPlanRequest){
        TreatmentPlan treatmentPlan=new TreatmentPlan();
        // im giving the responsibility to save the migrantHealthId for the Treatment Plan to the
        // service layer of TreatmentPlan
        treatmentPlan.setDiseaseCode(treatmentPlanRequest.getDiseaseCode());
        treatmentPlan.setStartDate(treatmentPlanRequest.getStartDate());
        treatmentPlan.setEndDate(treatmentPlanRequest.getEndDate());
        treatmentPlan.setMedicationList(treatmentPlanRequest.getMedicationList());
        treatmentPlan.setReminderTime(treatmentPlanRequest.getReminderTime());
        treatmentPlan.setFrequencyInDays(treatmentPlanRequest.getFrequencyInDays());
        treatmentPlan.setPreferredChannel(treatmentPlanRequest.getPreferredChannel());
        treatmentPlan.setPreferredLanguage(treatmentPlanRequest.getPreferredLanguage());
        treatmentPlan.setCreatedAt(LocalDateTime.now());
        // THE STATUS SHOULD BE SET BY THE SERVICE LAYER IF IT IS NOT YET STARTED OR ACTIVE
        return treatmentPlan;
    }

    public static TreatmentPlanResponse toResponse(TreatmentPlan savedTreatmentPlan) {
        return TreatmentPlanResponse.builder()
                .planId(savedTreatmentPlan.getPlanId())
                .diseaseCode(savedTreatmentPlan.getDiseaseCode())
                .startDate(savedTreatmentPlan.getStartDate())
                .endDate(savedTreatmentPlan.getEndDate())
                .medicationList(savedTreatmentPlan.getMedicationList())
                .reminderTime(savedTreatmentPlan.getReminderTime())
                .nextActionDue(savedTreatmentPlan.getNextActionDue())
                .status(savedTreatmentPlan.getStatus())
                .frequencyInDays(savedTreatmentPlan.getFrequencyInDays())
                .preferredChannel(savedTreatmentPlan.getPreferredChannel())
                .preferredLanguage(savedTreatmentPlan.getPreferredLanguage())
                .createdAt(savedTreatmentPlan.getCreatedAt())
                .build();
    }
}
