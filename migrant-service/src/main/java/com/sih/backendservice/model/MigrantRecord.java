package com.sih.backendservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="medical_records")
public class MigrantRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String clinicOrHospitalName;

    @Column(nullable = false)
    private String doctorName;

    @Column(columnDefinition = "TEXT")
    private String symptoms;


    private String diagnosisCode;
    private String diagnosisSystem;
    private String diagnosisDisplay;

    @Column(columnDefinition = "TEXT")
    private String treatmentPrescribed;

    // stores the URI of the prescription
    @Column(name = "prescription_image")
    private String prescription;


    @Column(name="report_files")
    @OneToMany(mappedBy = "migrantRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Report> reports;

    @CreatedDate
    @Column(nullable = false,name = "visit_date",updatable = false)
    private LocalDateTime visitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "migrant_id", nullable = false)
    @JsonBackReference
    private Migrant migrant;

//    @PrePersist
//    public void setDiagnosisSystem(){
//        if(this.diagnosisSystem==null || this.diagnosisSystem.isEmpty()) {
//            this.diagnosisSystem = "ICD-10";
//        }
}
