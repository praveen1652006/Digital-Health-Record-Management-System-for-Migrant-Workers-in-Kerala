package com.sih.backendservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MigrantResponseDto {

    private String name;

    private Integer age;

    private String gender;

    private String bloodGroup;

    private String employer;

    private String campLocation;

    private LocalDate dateOfBirth;
}
