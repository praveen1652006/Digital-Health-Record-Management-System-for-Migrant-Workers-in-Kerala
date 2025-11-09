package com.sih.backendservice.ApplicationConfig;


import com.sih.backendservice.eks.MedicalCode;
import com.sih.backendservice.model.IcdCode;
import com.sih.backendservice.repository.IcdCodeRepository;
import com.sih.backendservice.repository.MedicalCodeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataSyncService {

    private final IcdCodeRepository icdCodeRepository;

    private final MedicalCodeRepository medicalCodeRepository;

    @PostConstruct
    public boolean syncData(){
        if(medicalCodeRepository.count()>0){
            log.info("Elasticsearch index already populated. Skipping sync.");
            return false;
        }

        log.info("Starting data sync from database to Elasticsearch...");
        List<IcdCode> icdCodeList=icdCodeRepository.findAll();
        List<MedicalCode> medicalCodeList=icdCodeList.stream().map(code->{
            MedicalCode medicalCode=new MedicalCode();
            medicalCode.setId(code.getId());
            medicalCode.setCode(code.getCode());
            medicalCode.setDescription(code.getDescription());
            return medicalCode;
        }).toList();
        medicalCodeRepository.saveAll(medicalCodeList);
        return true;
    }
}
