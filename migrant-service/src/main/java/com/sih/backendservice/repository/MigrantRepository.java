package com.sih.backendservice.repository;

import com.sih.backendservice.model.Migrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MigrantRepository extends JpaRepository<Migrant,Integer> {
    Optional<Migrant> findByMigrantHealthId(UUID migrantHealthId);

    Optional<Migrant> findByMobileNumber(String mobileNumber);

    Optional<Migrant> findByAuthUserId(UUID authUserId);

    boolean existsByMigrantHealthId(UUID migrantHealthId);
}
