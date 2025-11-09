package com.sih.backendservice.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MigrantRecordRequestDto {

    @NotBlank(message = "Clinic or hospital name cannot be blank")
    @Size(max = 255)
    private String clinicOrHospitalName;

    @NotBlank(message = "Doctor name cannot be blank")
    @Size(max = 255)
    @Column(nullable = false)
    private String doctorName;

    @NotBlank(message = "Symptoms cannot be null")
    private String symptoms;

    @NotBlank(message = "Diagnosis cannot be blank")
    private String diagnosisDisplay;

    @NotBlank(message = "ICD code cannot be blank")
    private String diagnosisIcdCode;

    @NotBlank(message = "treatment Prescribed cannot be empty")
    private String treatmentPrescribed;


}
