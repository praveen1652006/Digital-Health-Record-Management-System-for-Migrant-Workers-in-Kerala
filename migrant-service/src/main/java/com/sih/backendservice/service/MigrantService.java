package com.sih.backendservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sih.backendservice.dto.request.MigrantRequestDto;
import com.sih.backendservice.dto.response.MigrantResponseDto;

import java.util.UUID;

public interface MigrantService {

    MigrantResponseDto registerMigrant(MigrantRequestDto migrant) throws JsonProcessingException;

    byte[] getMigrantQrCode(UUID healthId);
}
