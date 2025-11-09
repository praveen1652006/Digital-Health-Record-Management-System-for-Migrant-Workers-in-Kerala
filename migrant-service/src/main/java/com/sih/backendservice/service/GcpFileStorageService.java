package com.sih.backendservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GcpFileStorageService {

    String uploadPrescription(MultipartFile file, Integer medicalRecordId) throws IOException;

    List<String> uploadReports(List<MultipartFile> reportFiles, Integer medicalRecordId) throws IOException;

    String getPrescriptionUrl(Integer medicalRecordId);

    List<String> getReportFileUrls(Integer medicalRecordId);

}
