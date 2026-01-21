package com.jobfinder.infrastructure.loginandregister.controller;

import com.jobfinder.domain.login.LoginAndRegisterFacade;
import com.jobfinder.domain.login.dto.RegistrationResultDto;
import com.jobfinder.domain.login.dto.UserRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
class LoginAndRegisterController {

    private final LoginAndRegisterFacade loginAndRegisterFacade;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResultDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.password());
        RegistrationResultDto registrationResultDto = loginAndRegisterFacade.registerUser(userRequestDto.username(), encodedPassword);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResultDto);
    }
}
