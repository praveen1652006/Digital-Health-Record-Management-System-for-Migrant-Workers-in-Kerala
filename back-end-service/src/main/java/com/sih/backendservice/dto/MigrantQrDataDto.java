package com.sih.backendservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MigrantQrDataDto {
    private UUID migrantHealthId;
    private String name;
    private Integer age;
    private String gender;
    private String bloodGroup;
    private String employer;
    private String campLocation;
    private LocalDate dateOfBirth;
}
