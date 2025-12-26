package com.pc.eliminationservice.repository;

import com.pc.eliminationservice.model.ScheduledOccurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleOccurrenceRepository extends JpaRepository<ScheduledOccurrence, Long> {
}
