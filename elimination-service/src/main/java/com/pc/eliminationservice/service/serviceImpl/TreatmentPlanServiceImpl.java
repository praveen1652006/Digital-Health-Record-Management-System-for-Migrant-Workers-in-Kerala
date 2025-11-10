package com.pc.eliminationservice.service.serviceImpl;

import com.pc.eliminationservice.dto.request.TreatmentPlanRequest;
import com.pc.eliminationservice.dto.response.TreatmentPlanResponse;
import com.pc.eliminationservice.exception.MigrantNotFoundException;
import com.pc.eliminationservice.grpc.EliminationServiceGrpcClient;
import com.pc.eliminationservice.mapper.TreatmentMapper;
import com.pc.eliminationservice.model.TreatmentPlan;
import com.pc.eliminationservice.model.enums.TreatmentStatus;
import com.pc.eliminationservice.repository.TreatmentPlanRepository;
import com.pc.eliminationservice.service.TreatmentPlanService;
import com.sih.backendService.grpc.MigrantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TreatmentPlanServiceImpl implements TreatmentPlanService {

    private final TreatmentPlanRepository treatmentPlanRepository;

    private final EliminationServiceGrpcClient eliminationServiceGrpcClient;

    @Override
    public TreatmentPlanResponse createTreatmentPlanForTheMigrant(UUID migrantHealthId, TreatmentPlanRequest treatmentPlanRequest) {

        // check if the migrant exists
        MigrantResponse response=eliminationServiceGrpcClient.getPatientById(migrantHealthId);
        if(!response.getExists()){
            throw new MigrantNotFoundException("Migrant with the healthId Not found: "+ migrantHealthId);
        }

        if (treatmentPlanRequest.getEndDate() != null &&
                treatmentPlanRequest.getStartDate().isAfter(treatmentPlanRequest.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        TreatmentPlan treatmentPlan= TreatmentMapper.toTreatmentPlan(treatmentPlanRequest);
        treatmentPlan.setMigrantHealthId(migrantHealthId);

        if(!LocalDate.now().isEqual(treatmentPlanRequest.getStartDate())){
            treatmentPlan.setStatus(TreatmentStatus.NOT_STARTED);
        }
        else {
            treatmentPlan.setStatus(TreatmentStatus.ACTIVE);
        }
        TreatmentPlan savedTreatmentPlan=treatmentPlanRepository.save(treatmentPlan);

        return TreatmentMapper.toResponse(savedTreatmentPlan);
    }
}
