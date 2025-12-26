package com.pc.eliminationservice.mapper;

import com.pc.eliminationservice.dto.request.ScheduleRuleRequest;
import com.pc.eliminationservice.model.ScheduleRule;
import com.pc.eliminationservice.model.TreatmentPlan;

public class ScheduleMapper {
    public static ScheduleRule toEntity(ScheduleRuleRequest scheduleRuleRequest, TreatmentPlan treatmentPlan) {
        ScheduleRule scheduleRule=new ScheduleRule();
        scheduleRule.setScheduleType(scheduleRuleRequest.getScheduleType());
        scheduleRule.setFrequencyInDays(scheduleRuleRequest.getFrequencyInDays());
        scheduleRule.setRepeatCount(scheduleRuleRequest.getRepeatCount());
        scheduleRule.setCustomOccurrencePerPeriod(scheduleRuleRequest.getCustomOccurrencePerPeriod());
        scheduleRule.setStartDate(scheduleRuleRequest.getStartDate());
        scheduleRule.setEndDate(scheduleRuleRequest.getEndDate());
        scheduleRule.setReminderTime(scheduleRuleRequest.getReminderTime());
        scheduleRule.setReminderWindowDays(scheduleRuleRequest.getReminderWindowDays());
        scheduleRule.setTreatmentPlan(treatmentPlan);
        return scheduleRule;
    }
}
