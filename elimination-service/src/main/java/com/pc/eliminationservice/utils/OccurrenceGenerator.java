package com.pc.eliminationservice.utils;

import com.pc.eliminationservice.model.ScheduleRule;
import com.pc.eliminationservice.model.ScheduledOccurrence;
import com.pc.eliminationservice.model.enums.OccurrenceStatus;
import com.pc.eliminationservice.repository.ScheduleOccurrenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OccurrenceGenerator {

    private final ScheduleOccurrenceRepository occurrenceRepository;

    public List<ScheduledOccurrence> generateOccurrences(ScheduleRule rule){
        List<ScheduledOccurrence> occurrencesList=new ArrayList<>();

        LocalDate startDate=rule.getStartDate();

       // Don't generate infinite events. Set a reasonable max (e.g., 2 years)
        LocalDate hardStopDate = LocalDate.now().plusYears(2);

        int count=0;
        while(shouldContinue(rule, count, startDate, hardStopDate)){
            ScheduledOccurrence occurrence=new ScheduledOccurrence();
            occurrence.setTreatmentPlan(rule.getTreatmentPlan());
            occurrence.setScheduleRule(rule);
            occurrence.setDueDate(startDate);

            int reminderDays= rule.getReminderWindowDays()!=null ? rule.getReminderWindowDays() : 0;
            occurrence.setRemainderWindowStart(startDate.minusDays(reminderDays));

            occurrence.setRemainderWindowEnd(startDate);

            occurrence.setStatus(OccurrenceStatus.PENDING);

            occurrencesList.add(occurrence);

            startDate=calculateNextDate(startDate, rule);
            count++;
        }
        log.info("Generated {} occurrences for rule {}", occurrencesList.size(), rule.getRuleId());
        return occurrenceRepository.saveAll(occurrencesList);
    }

    private boolean shouldContinue(ScheduleRule rule,
                                   int count,
                                   LocalDate startDate,
                                   LocalDate hardStopDate) {

        if(startDate.isAfter(hardStopDate)) return false;

        if(rule.getEndDate()!=null) return !startDate.isAfter(rule.getEndDate());

        if(rule.getRepeatCount()!=null) return count<rule.getRepeatCount();

        return false;

    }

    private LocalDate calculateNextDate(LocalDate startDate, ScheduleRule rule) {
        int frequency= rule.getFrequencyInDays()!=null ? rule.getFrequencyInDays() : 1;

        return switch (rule.getScheduleType()) {
            case DAILY -> startDate.plusDays(frequency);
            case WEEKLY -> startDate.plusWeeks(frequency);
            case MONTHLY -> startDate.plusMonths(frequency);
            case CUSTOM -> startDate.plusDays(rule.getCustomOccurrencePerPeriod());
            default -> throw new IllegalArgumentException("Unsupported schedule type: " + rule.getScheduleType());
        };
    }

}
