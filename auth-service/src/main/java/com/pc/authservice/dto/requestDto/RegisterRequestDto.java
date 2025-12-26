package com.pc.authservice.dto.requestDto;

import com.pc.authservice.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequestDto {

    private String name;

    @Email(message = "Invalid email")
    private String identifier;

    @NotBlank(message = "Mobile Number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 Digits")
    @Column(name = "mobile_number", unique = true, nullable = false)
    private String mobileNumber;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


}
