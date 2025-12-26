package com.pc.eliminationservice.repository;

import com.pc.eliminationservice.model.ScheduleRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRuleRepository extends JpaRepository<ScheduleRule, Integer> {
}
