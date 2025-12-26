package com.pc.authservice.controller;


import com.pc.authservice.dto.requestDto.LoginRequestDto;
import com.pc.authservice.dto.requestDto.RegisterRequestDto;
import com.pc.authservice.dto.responseDto.AuthenticationResponseDto;
import com.pc.authservice.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody @Valid LoginRequestDto request){
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestParam("Authorization") String authHeader){
        boolean isValid = authenticationService.validateToken(authHeader);
        return isValid ? ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
