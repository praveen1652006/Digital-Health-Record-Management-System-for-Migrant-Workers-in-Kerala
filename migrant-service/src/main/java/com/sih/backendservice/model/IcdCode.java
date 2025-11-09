package com.sih.backendservice.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "icd_code")
public class IcdCode {

    @Id
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String code;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String description;
}
