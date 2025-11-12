package com.pc.eliminationservice.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medication {

    private String medicationName;

    private String medicationDosage;

    private String dosage;
}
