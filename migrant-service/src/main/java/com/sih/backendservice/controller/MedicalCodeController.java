package com.sih.backendservice.controller;

import com.sih.backendservice.eks.MedicalCode;
import com.sih.backendservice.ApplicationConfig.MedicalCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/search/diagnosis")
public class MedicalCodeController {

    private final MedicalCodeService medicalCodeService;

    @GetMapping("/{description}")
    public ResponseEntity<List<MedicalCode>> getDescription(@PathVariable String description){
        return ResponseEntity.status(HttpStatus.OK).body(medicalCodeService.getAllByDescription(description));
    }
}
