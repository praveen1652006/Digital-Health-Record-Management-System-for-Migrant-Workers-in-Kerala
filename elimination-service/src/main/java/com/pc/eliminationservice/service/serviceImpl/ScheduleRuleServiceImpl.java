package com.pc.eliminationservice.service.serviceImpl;

import com.pc.eliminationservice.dto.request.ScheduleRuleRequest;
import com.pc.eliminationservice.exception.TreatmentPlanNotFoundException;
import com.pc.eliminationservice.mapper.ScheduleMapper;
import com.pc.eliminationservice.model.ScheduleRule;
import com.pc.eliminationservice.model.ScheduledOccurrence;
import com.pc.eliminationservice.model.TreatmentPlan;
import com.pc.eliminationservice.repository.ScheduleRuleRepository;
import com.pc.eliminationservice.repository.TreatmentPlanRepository;
import com.pc.eliminationservice.service.ScheduleRuleService;
import com.pc.eliminationservice.utils.OccurrenceGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleRuleServiceImpl implements ScheduleRuleService {

    private final ScheduleRuleRepository scheduleRuleRepository;

    private final TreatmentPlanRepository treatmentPlanRepository;

    private final OccurrenceGenerator occurrenceGenerator;

    @Override
    @Transactional
    public void createScheduleRule(Integer planId, ScheduleRuleRequest scheduleRuleRequest) {
        TreatmentPlan treatmentPlan=treatmentPlanRepository.findById(planId.longValue())
                .orElseThrow(()->new TreatmentPlanNotFoundException("Treatment plan not found with id: "+planId));

        ScheduleRule scheduleRule=ScheduleMapper.toEntity(scheduleRuleRequest, treatmentPlan);
        scheduleRuleRepository.save(scheduleRule);

        List<ScheduledOccurrence> occurrences=occurrenceGenerator.generateOccurrences(scheduleRule);

        scheduleRule.setOccurrences(occurrences);

        log.info("Schedule has been generated for the Treatment with id {} {}",planId, scheduleRule.toString());

        scheduleRuleRepository.save(scheduleRule);

        // emit the kafka event to the respective service that handles
    }
}
