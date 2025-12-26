package com.pc.eliminationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "treatment_plan")
public class TreatmentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(name = "migrant_health_id", nullable = false)
    private UUID migrantHealthId;

    @Column(nullable = false)
    private String diseaseCode; // it denotes what treatment it is

    private LocalDate startDate;

    private LocalDate endDate;


    @ElementCollection
    @CollectionTable(
            name = "treatment_plan_medications",
            joinColumns = @JoinColumn(name = "plan_id")
    )
    private List<Medication> medicationList;

    @Enumerated(EnumType.STRING)
    private TreatmentStatus status;


    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "treatmentPlan", cascade = CascadeType.ALL)
    private List<ScheduleRule> scheduleRule;


    @JsonManagedReference
    @OneToMany(mappedBy = "treatmentPlan"
    ,cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<TreatmentLog> treatmentLogs=new ArrayList<>();
>>>>>>> Stashed changes
}

// from 9:00 AM onwards it begins to call the patient
//    private LocalTime reminderTime;

//    @Column(name = "next-action_due")
//    private LocalDateTime nextActionDue; // IMP
//    private Integer frequencyInDays; // IMP


//    // communication Settings for flexibility
//    private String preferredChannel;
//
//    private String preferredLanguage;
//
//    private Integer reminderRetryCount=0; // IMP
