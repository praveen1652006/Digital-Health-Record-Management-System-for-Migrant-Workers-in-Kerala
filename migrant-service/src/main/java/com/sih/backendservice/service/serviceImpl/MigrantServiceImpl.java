package com.sih.backendservice.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sih.backendservice.ApplicationConfig.Utils;
import com.sih.backendservice.dto.MigrantQrDataDto;
import com.sih.backendservice.dto.request.MigrantRequestDto;
import com.sih.backendservice.dto.response.MigrantResponseDto;
import com.sih.backendservice.mapper.MigrantMapper;
import com.sih.backendservice.model.Migrant;
import com.sih.backendservice.repository.MigrantRepository;
import com.sih.backendservice.service.MigrantService;
import com.sih.backendservice.service.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MigrantServiceImpl implements MigrantService {

    private final MigrantRepository migrantRepository;

    private final QrCodeService qrCodeService;

    public MigrantResponseDto registerMigrant(MigrantRequestDto migrant) throws JsonProcessingException {
        Migrant savedMigrant=migrantRepository.save(MigrantMapper.toMigrant(migrant));

        // creating the DTO with the Data for the Qr code
        MigrantQrDataDto qrData=new MigrantQrDataDto(
                savedMigrant.getMigrantHealthId(), savedMigrant.getName(), savedMigrant.getAge(),
                savedMigrant.getGender(), savedMigrant.getBloodGroup(), savedMigrant.getEmployer(),
                savedMigrant.getCampLocation(), savedMigrant.getDateOfBirth()
        );

        // Generating the uncompressed QrCodeImage
        byte[] qrCodeData= qrCodeService.createQrCode(qrData);

        // Compress the image
        byte[] compressesQrCode= Utils.compressImage(qrCodeData);

        savedMigrant.setQrCodeImage(compressesQrCode);

        // after generating the Qr just saving the migrant again in db
        migrantRepository.save(savedMigrant);

        return MigrantMapper.toMigrantResponseDto(savedMigrant);
    }

    @Transactional(readOnly = true)
    public byte[] getMigrantQrCode(UUID healthId) {
        Migrant migrant = migrantRepository.findByMigrantHealthId(healthId)
                .orElseThrow(() -> new RuntimeException("Migrant not found..."));

        byte[] compressedQrCode = migrant.getQrCodeImage();
        if (compressedQrCode == null) {
            return null;
        }

        return Utils.decompressImage(compressedQrCode);
    }
}
