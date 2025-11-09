package com.sih.backendservice.repository;

import com.sih.backendservice.model.MigrantRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface MigrantRecordRepository extends JpaRepository<MigrantRecord, Integer> {
    List<MigrantRecord> findByMigrantMigrantHealthIdOrderByVisitDateDesc(UUID migrantMigrantHealthId);
}
