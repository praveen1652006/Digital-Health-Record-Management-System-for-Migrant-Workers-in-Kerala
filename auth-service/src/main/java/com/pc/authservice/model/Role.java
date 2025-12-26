package com.pc.authservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pc.authservice.model.Permission.*;

@RequiredArgsConstructor
public enum Role {
    USER(Set.of(USER_READ)),     // Migrant or Patient

    DOCTOR(Set.of(
            USER_READ,
            USER_UPDATE,
            USER_DELETE,
            USER_CREATE
    )),

    ADMIN(Set.of(
//            ADMIN_CREATE,
//            ADMIN_UPDATE,
//            ADMIN_DELETE,
//            ADMIN_READ,
            DOCTOR_CREATE,
            DOCTOR_UPDATE,
            DOCTOR_READ,
            DOCTOR_DELETE
    ));

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities=getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
