package com.pc.authservice.service;

import com.pc.authservice.configuration.JwtService;
import com.pc.authservice.dto.requestDto.LoginRequestDto;
import com.pc.authservice.dto.requestDto.RegisterRequestDto;
import com.pc.authservice.dto.responseDto.AuthenticationResponseDto;
import com.pc.authservice.model.User;
import com.pc.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authManager;

    @Transactional
    public String register( RegisterRequestDto request) {

        if(userRepository.findByIdentifier(request.getIdentifier()).isPresent()){
            log.error("Identifier with user already exists, Try using a different email address");
            throw new RuntimeException("Identifier with user already exists, Try using a different email address");
        }

        var user = User.builder()
                .name(request.getName())
                .identifier(request.getIdentifier())
                .mobileNumber(request.getMobileNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(user);

        log.info("User with ID: {} Registered Successfully", savedUser.getId());

        return "User Registered Successfully";
    }

    public AuthenticationResponseDto login(LoginRequestDto request){
        log.info("Attempting login for Identifier: {}", request.getIdentifier());
        var user = userRepository.findByIdentifier(request.getIdentifier())
                .orElseThrow(()->{
                    log.error("User not found for Identifier: {}", request.getIdentifier());
                    return new UsernameNotFoundException("User not found");
                });

        log.info("User found attempting authentication");

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getIdentifier(),
                        request.getPassword()
                )
        );
        log.info("User authenticated successfully");

        var jwtToken=jwtService.generateToken(user);

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
<<<<<<< Updated upstream

    public boolean validateToken(String authHeader) {
        return jwtService.validateToken(authHeader.substring(7));
    }
=======
>>>>>>> Stashed changes
}
