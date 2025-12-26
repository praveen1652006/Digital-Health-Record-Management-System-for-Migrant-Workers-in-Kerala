package com.sih.backendservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "migrants")
public class Migrant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "migrant_health_id", unique = true, nullable = false, updatable = false)
    private UUID migrantHealthId;

    @Column(name = "auth_user_id", unique = true)
    private UUID authUserId; // new microservice setup

    @NotBlank(message = "Aadhaar number cannot be blank")
    @Pattern(regexp = "^\\d{12}$", message = "Aadhaar number must be exactly 12 digits")
    @Column(name = "aadhaar_number", unique = true, nullable = false, length = 12)
    private String aadhaarNumber;

    @Column(name = "registered_by_provider_id", nullable = false, updatable = false)
    private UUID registeredByProviderId; // new -> just for logging

    @NotBlank(message = "Mobile Number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 Digits")
    @Column(name = "mobile_number", unique = true, nullable = false)
    @JsonIgnore
    private String mobileNumber; // new

    private String name;

    private Integer age;

    private String gender;

    private String bloodGroup;

    private String employer; // where does the migrant currently work?

    private String campLocation; // where does the migrant lives?

    private LocalDate dateOfBirth;

    @Lob
    @Column(name = "qr_code_image")
    private byte[] qrCodeImage;

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "migrant", cascade = CascadeType.ALL,orphanRemoval = true, fetch=FetchType.LAZY)
    @JsonManagedReference
    private List<MigrantRecord> migrantRecords;


    @PrePersist
    public void generateId() {
        if (this.migrantHealthId == null) {
            this.migrantHealthId = UUID.randomUUID();
        }
    }
}
