package com.sih.backendservice.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sih.backendservice.dto.MigrantQrDataDto;
import com.sih.backendservice.service.QrCodeService;
import lombok.RequiredArgsConstructor;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QrCodeServiceImpl implements QrCodeService {
    private final ObjectMapper objectMapper;

    public byte[] createQrCode(MigrantQrDataDto migrantData) throws JsonProcessingException {
        String jsonContent= objectMapper.writeValueAsString(migrantData);
        return QRCode
                .from(jsonContent)
                .withSize(250, 250)
                .stream()
                .toByteArray();
    }
}
