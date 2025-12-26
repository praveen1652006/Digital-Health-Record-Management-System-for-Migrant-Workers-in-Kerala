package com.pc.authservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
        DOCTOR_READ("doctor:read"),
        DOCTOR_UPDATE("doctor:update"),
        DOCTOR_CREATE("doctor:create"),
        DOCTOR_DELETE("doctor:delete"),
        USER_READ("user:read"), // migrant/patient in this case
        USER_UPDATE("admin:update"),
        USER_CREATE("admin:create"),
        USER_DELETE("admin_delete");


        @Getter
        private final String permission;
}
