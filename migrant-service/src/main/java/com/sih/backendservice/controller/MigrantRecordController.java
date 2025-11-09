package com.sih.backendservice.controller;

import com.sih.backendservice.dto.request.MigrantRecordRequestDto;
import com.sih.backendservice.model.MigrantRecord;
import com.sih.backendservice.service.MigrantRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/health-records")
@RequiredArgsConstructor
public class MigrantRecordController {

    private final MigrantRecordService migrantRecordService;

    @GetMapping("/{migrantHealthId}")
    public ResponseEntity<List<MigrantRecord>> getHistory(@PathVariable UUID migrantHealthId){
        return ResponseEntity.status(HttpStatus.OK).body(migrantRecordService.getMedicalHistory(migrantHealthId));
    }

    @PostMapping("/add/{migrantHealthId}")
    public ResponseEntity<String> createHealthRecord(
            @PathVariable UUID migrantHealthId,
            @RequestPart("medicalRecordRequest") MigrantRecordRequestDto medicalRecordRequest,
            @RequestPart("prescriptionFile")MultipartFile prescriptionFile,
            @RequestPart("reportFiles")List<MultipartFile> reportFiles) throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(migrantRecordService.createHealthRecord(migrantHealthId, medicalRecordRequest, prescriptionFile, reportFiles));

    }

    @GetMapping("prescription/{medicalRecordId}")
    public ResponseEntity<String> getPrescription(@PathVariable Integer medicalRecordId){
        return ResponseEntity.status(HttpStatus.OK).body(migrantRecordService.getPrescriptionByHealthId(medicalRecordId));
    }
    @GetMapping("reportFiles/{medicalRecordId}")
    public ResponseEntity<List<String>>getReportFiles(@PathVariable Integer medicalRecordId){
        return ResponseEntity.status(HttpStatus.OK).body(migrantRecordService.getReportFiles(medicalRecordId));
    }
}
