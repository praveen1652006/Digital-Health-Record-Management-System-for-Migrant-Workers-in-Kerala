package com.sih.backendservice.mapper;

import com.sih.backendservice.dto.request.MigrantRecordRequestDto;
import com.sih.backendservice.model.MigrantRecord;
import com.sih.backendservice.model.Migrant;

public class MigrantRecordMapper {

    public static MigrantRecord toMedicalRecord(MigrantRecordRequestDto migrantRecordRequestDto, Migrant migrant){
        MigrantRecord migrantRecord =new MigrantRecord();
        migrantRecord.setClinicOrHospitalName(migrantRecordRequestDto.getClinicOrHospitalName());
        migrantRecord.setDoctorName(migrantRecordRequestDto.getDoctorName());
        migrantRecord.setSymptoms(migrantRecordRequestDto.getSymptoms());
        migrantRecord.setDiagnosisSystem("ICD-10");
        migrantRecord.setDiagnosisDisplay(migrantRecordRequestDto.getDiagnosisDisplay());
        migrantRecord.setDiagnosisCode(migrantRecordRequestDto.getDiagnosisIcdCode());
        migrantRecord.setMigrant(migrant);
        return migrantRecord;
    }
}
