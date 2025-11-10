package com.pc.eliminationservice.controller;

import com.pc.eliminationservice.dto.request.TreatmentPlanRequest;
import com.pc.eliminationservice.dto.response.TreatmentPlanResponse;
import com.pc.eliminationservice.model.TreatmentPlan;
import com.pc.eliminationservice.service.TreatmentPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/treatment")
@RequiredArgsConstructor
public class TreatmentPlanController {

    private final TreatmentPlanService treatmentPlanService;

    @PostMapping("/{migrantHealthId}")
    public ResponseEntity<TreatmentPlanResponse> createTreatmentPlan(@PathVariable UUID migrantHealthId,
                                                                     @RequestBody TreatmentPlanRequest treatmentPlanRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(treatmentPlanService.createTreatmentPlanForTheMigrant(migrantHealthId, treatmentPlanRequest));
    }
}
