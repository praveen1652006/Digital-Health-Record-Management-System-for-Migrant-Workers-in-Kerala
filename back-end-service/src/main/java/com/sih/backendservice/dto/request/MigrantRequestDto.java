package com.sih.backendservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MigrantRequestDto {

    @NotBlank(message = "Aadhaar number cannot be blank")
    @Pattern(regexp = "^\\d{12}$", message = "Aadhaar number must be exactly 12 digits")
    private String aadharNumber;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "age cannot be null")
    private Integer age;

    @NotBlank(message = "Gender cannot be empty or null")
    private String gender;

    @NotBlank(message = "bloodGroup cannot be null or empty")
    private String bloodGroup;

    @NotBlank(message = "Either if works or not there should be input")
    private String employer; // where does the migrant currently work?

    @NotBlank(message = "the migrant's staying place should be included")
    private String campLocation; // where does the migrant lives?

    @NotNull(message = "BirthDate cannot be null")
    private LocalDate dateOfBirth;
}
