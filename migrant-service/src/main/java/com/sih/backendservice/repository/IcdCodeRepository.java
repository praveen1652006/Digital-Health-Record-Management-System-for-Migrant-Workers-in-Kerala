package com.sih.backendservice.repository;

import com.sih.backendservice.model.IcdCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IcdCodeRepository extends JpaRepository<IcdCode,Integer> {
}
