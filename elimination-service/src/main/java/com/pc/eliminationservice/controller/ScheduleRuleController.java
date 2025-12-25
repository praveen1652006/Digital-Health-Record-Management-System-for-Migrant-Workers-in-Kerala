package com.pc.eliminationservice.controller;

import com.pc.eliminationservice.dto.request.ScheduleRuleRequest;
import com.pc.eliminationservice.model.ScheduleRule;
import com.pc.eliminationservice.service.ScheduleRuleService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/treatments")
@RequiredArgsConstructor
public class ScheduleRuleController {

    private final ScheduleRuleService scheduleService;

    @PostMapping("/schedule-rule/{planId}")
    public ResponseEntity<Void> createSchedule(@PathVariable Integer planId,
                                               @RequestBody ScheduleRuleRequest scheduleRuleRequest){
        scheduleService.createScheduleRule(planId, scheduleRuleRequest);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
