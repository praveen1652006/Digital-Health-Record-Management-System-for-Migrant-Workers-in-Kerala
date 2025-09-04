package com.sih.backendservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sih.backendservice.ApplicationConfig.Utils;
import com.sih.backendservice.dto.request.MigrantRequestDto;
import com.sih.backendservice.exceptionHandler.MigrantNotFoundException;
import com.sih.backendservice.model.Migrant;
import com.sih.backendservice.repository.MigrantRepository;
import com.sih.backendservice.service.MigrantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/migrant")
public class MigrantController {

    private final MigrantService migrantService;

    // for now, we are returning the migrant alone, after implementing the security
    // we can generate a JwT token, and then we return it to the FE

    @PostMapping("")
    public ResponseEntity<Migrant> registerMigrant(@RequestBody MigrantRequestDto migrant) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body(migrantService.registerMigrant(migrant));
    }

    @GetMapping("/qrcode/{healthId}")
    public ResponseEntity<byte[]> getQrCode(@PathVariable UUID healthId){
        byte[] decompressedQrCode = migrantService.getMigrantQrCode(healthId);

        if (decompressedQrCode == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(decompressedQrCode);    }
}
