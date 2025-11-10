package com.pc.eliminationservice.repository;

import com.pc.eliminationservice.model.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {
}
