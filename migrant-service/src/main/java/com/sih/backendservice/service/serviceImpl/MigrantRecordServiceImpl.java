package com.sih.backendservice.service.serviceImpl;

import com.sih.backendservice.dto.request.MigrantRecordRequestDto;
import com.sih.backendservice.exceptionHandler.exceptions.MigrantNotFoundException;
import com.sih.backendservice.mapper.MigrantRecordMapper;
import com.sih.backendservice.model.Migrant;
import com.sih.backendservice.model.MigrantRecord;
import com.sih.backendservice.repository.MigrantRecordRepository;
import com.sih.backendservice.repository.MigrantRepository;
import com.sih.backendservice.service.GcpFileStorageService;
import com.sih.backendservice.service.MigrantRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MigrantRecordServiceImpl implements MigrantRecordService {

    private final MigrantRecordRepository migrantRecordRepository;

    private final MigrantRepository migrantRepository;

    private final GcpFileStorageService gcpFileStorageService;

    public List<MigrantRecord> getMedicalHistory(UUID migrantHealthId) {
        if(!migrantRepository.existsByMigrantHealthId(migrantHealthId)){
            throw new MigrantNotFoundException("Migrant not found with ID: "+ migrantHealthId);
        }

        return migrantRecordRepository.findByMigrantMigrantHealthIdOrderByVisitDateDesc(migrantHealthId);
    }

    @Transactional // each record can be considered as one visit
    public String createHealthRecord(UUID migrantHealthId,
                                     MigrantRecordRequestDto medicalRecordRequest,
                                     MultipartFile prescriptionFile,
                                     List<MultipartFile> reportFiles) throws IOException {

        Migrant migrant=migrantRepository.findByMigrantHealthId(migrantHealthId)
                .orElseThrow(()-> new MigrantNotFoundException("firstly, create a migrant and then u can allowed to create a record for them"));

        // storing the medical record without prescription and report files
        MigrantRecord migrantRecord = MigrantRecordMapper.toMedicalRecord(medicalRecordRequest, migrant);
        MigrantRecord savedRecord=migrantRecordRepository.save(migrantRecord);
        if(prescriptionFile==null || prescriptionFile.isEmpty()){
            throw new RuntimeException("Please add again the prescription");
        }
        String prescriptionPath=gcpFileStorageService.uploadPrescription(prescriptionFile, savedRecord.getId());
        List<String> reportPaths=gcpFileStorageService.uploadReports(reportFiles, savedRecord.getId());
        return "Medical Record Created Successfully For UserId: "+ migrantHealthId;
    }

    public String getPrescriptionByHealthId(Integer medicalRecordId) {
        MigrantRecord migrantRecord=migrantRecordRepository.findById(medicalRecordId)
                .orElseThrow(()-> new RuntimeException("Record isn't create properly, create the record and try again"));
        return gcpFileStorageService.getPrescriptionUrl(migrantRecord.getId());
    }

    public List<String> getReportFiles(Integer medicalRecordId) {
        MigrantRecord migrantRecord=migrantRecordRepository.findById(medicalRecordId)
                .orElseThrow(()-> new RuntimeException("Record isn't create properly, create the record and try again"));
        return gcpFileStorageService.getReportFileUrls(medicalRecordId);
    }
}
