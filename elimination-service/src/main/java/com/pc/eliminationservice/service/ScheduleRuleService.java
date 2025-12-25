package com.pc.eliminationservice.service;

import com.pc.eliminationservice.dto.request.ScheduleRuleRequest;

public interface ScheduleRuleService {

    void createScheduleRule(Integer planId, ScheduleRuleRequest scheduleRuleRequest);
}
