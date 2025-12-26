package com.pc.eliminationservice.service;

import com.pc.eliminationservice.dto.request.TreatmentPlanRequest;
import com.pc.eliminationservice.dto.response.TreatmentPlanResponse;

import java.util.List;
import java.util.UUID;

public interface TreatmentPlanService {
    TreatmentPlanResponse createTreatmentPlanForTheMigrant(UUID migrantHealthId, TreatmentPlanRequest treatmentPlanRequest);

    List<TreatmentPlanResponse> getAllTreatmentPlanById(UUID migrantHealthId);
}
