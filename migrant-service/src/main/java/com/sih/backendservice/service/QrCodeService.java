package com.sih.backendservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sih.backendservice.dto.MigrantQrDataDto;

public interface QrCodeService {

    byte[] createQrCode(MigrantQrDataDto migrantData) throws JsonProcessingException;
}
