package com.sih.backendservice.service.serviceImpl;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.sih.backendservice.model.MigrantRecord;
import com.sih.backendservice.model.Report;
import com.sih.backendservice.repository.MigrantRecordRepository;
import com.sih.backendservice.repository.ReportRepository;
import com.sih.backendservice.service.GcpFileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class GcpServiceImpl implements GcpFileStorageService {
    private final Storage storage;

    private final MigrantRecordRepository migrantRecordRepository;

    private final ReportRepository reportRepository;

    @Value("${gcp.bucket.name}")
    private String bucketName;

    @Value("${gcp-cloud-medical-record-directory}")
    private String mrDirectory;

    @Value("${gcp-cloud-report-file-directory}")
    private String rfDirectory;

    public String uploadPrescription(MultipartFile file, Integer medicalRecordId) throws IOException {
        // Generate a unique path with subdirectory
        String fileName=mrDirectory+"/"+ UUID.randomUUID()+"-"+file.getOriginalFilename();

        BlobId blobId=BlobId.of(bucketName, fileName);
        BlobInfo blobInfo=BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();
        // saving to ṭhe cloud
        storage.create(blobInfo, file.getBytes());

        MigrantRecord migrantRecord=migrantRecordRepository.findById(medicalRecordId)
                .orElseThrow(()-> new RuntimeException("Record not created, create a Medical Record firstly"));
        migrantRecord.setPrescription(fileName);
        migrantRecordRepository.save(migrantRecord);

        return fileName;
    }

    @Transactional
    public List<String> uploadReports(List<MultipartFile> reportFiles, Integer medicalRecordId) throws IOException{
        MigrantRecord migrantRecord=migrantRecordRepository.findById(medicalRecordId)
                .orElseThrow(()-> new RuntimeException("Record not created, create a Medical Record firstly"));

        List<String> reportPaths=new ArrayList<>();

        for(MultipartFile reportFile:reportFiles){
            String reportFileName=rfDirectory+"/"+ UUID.randomUUID()+"-"+reportFile.getOriginalFilename();
            BlobId blobId=BlobId.of(bucketName, reportFileName);
            BlobInfo blobInfo=BlobInfo.newBuilder(blobId)
                    .setContentType(reportFile.getContentType())
                    .build();
            // saving it in the cloud
            storage.create(blobInfo, reportFile.getBytes());

            Report report=new Report();
            report.setReportName(reportFile.getOriginalFilename());
            report.setFileUrl(reportFileName);
            report.setReportType("Medical Report");
            report.setMimeType(reportFile.getContentType());
            report.setFileSize(reportFile.getSize());
            report.setMigrantRecord(migrantRecord);
            reportRepository.save(report);

            reportPaths.add(reportFileName);
        }
        return reportPaths;
    }

    public String getPrescriptionUrl(Integer medicalRecordId){
        MigrantRecord migrantRecord=migrantRecordRepository.findById(medicalRecordId)
                .orElseThrow(()-> new RuntimeException("Firstly create a medical record to add a prescription"));
        if(migrantRecord.getPrescription()==null){
            throw new RuntimeException("No prescription uploaded for this medical record, add a record to get");
        }
        BlobId blobId=BlobId.of(bucketName, migrantRecord.getPrescription());
        BlobInfo blobInfo= BlobInfo.newBuilder(blobId).build();
        URL signedUrl=storage.signUrl(blobInfo, 45, TimeUnit.MINUTES);
        return signedUrl.toString();
    }

    public List<String> getReportFileUrls(Integer medicalRecordId){
        MigrantRecord migrantRecord=migrantRecordRepository.findById(medicalRecordId)
                .orElseThrow(()-> new RuntimeException("Firstly create a medical record to add a prescription"));
        if(migrantRecord.getReports().isEmpty()){
            throw new RuntimeException("There is Reports found for the particular medicalRecord, firstly add them");
        }
        List<Report> reports=migrantRecord.getReports();
        List<String> signedUrls= new ArrayList<>();
        for(Report report: reports){
            BlobId blobId=BlobId.of(bucketName, report.getFileUrl());
            BlobInfo blobInfo= BlobInfo.newBuilder(blobId).build();
            signedUrls.add(storage.signUrl(blobInfo, 45, TimeUnit.MINUTES).toString());
        }
        return signedUrls;
    }


    // In Feature delete and update methods are need to be added

//    public Blob getPrescription(String fileName){
//        return storage.get(bucketName, fileName);
//    }
}
