package com.sih.backendservice.service;


import com.sih.backendservice.dto.request.MigrantRecordRequestDto;
import com.sih.backendservice.model.MigrantRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface MigrantRecordService {


    List<MigrantRecord> getMedicalHistory(UUID migrantHealthId);

    String createHealthRecord(UUID migrantHealthId,
                              MigrantRecordRequestDto medicalRecordRequest,
                              MultipartFile prescriptionFile,
                              List<MultipartFile> reportFiles) throws IOException;

    String getPrescriptionByHealthId(Integer medicalRecordId);

    List<String> getReportFiles(Integer medicalRecordId);
}
