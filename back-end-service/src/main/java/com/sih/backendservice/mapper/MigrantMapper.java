package com.sih.backendservice.mapper;

import com.sih.backendservice.dto.request.MigrantRequestDto;
import com.sih.backendservice.model.Migrant;

public class MigrantMapper {

    public static Migrant toMigrant(MigrantRequestDto migrantRequestDto){
        Migrant migrant=new Migrant();
        migrant.setAadhaarNumber(migrantRequestDto.getAadharNumber());
        migrant.setName(migrantRequestDto.getName());
        migrant.setAge(migrantRequestDto.getAge());
        migrant.setGender(migrantRequestDto.getGender());
        migrant.setBloodGroup(migrant.getBloodGroup());
        migrant.setEmployer(migrantRequestDto.getEmployer());
        migrant.setCampLocation(migrant.getCampLocation());
        migrant.setDateOfBirth(migrantRequestDto.getDateOfBirth());
        return migrant;
    }
}
