package com.sih.backendservice.ApplicationConfig;

import com.sih.backendservice.eks.MedicalCode;
import com.sih.backendservice.repository.MedicalCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalCodeService {

    private final MedicalCodeRepository medicalCodeRepository;

    public List<MedicalCode> getAllByDescription(String description){
        Pageable pageable = PageRequest.of(0, 50);

        return medicalCodeRepository.searchByTerm(description, pageable);
    }
}
