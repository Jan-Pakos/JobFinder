package com.jobfinder.infrastructure.loginandregister.controller;

import com.jobfinder.infrastructure.loginandregister.controller.dto.JwtResponseDto;
import com.jobfinder.infrastructure.loginandregister.controller.dto.LoginRequestDto;
import com.jobfinder.infrastructure.security.jwt.jwtAuthenticatorFacade;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
class TokenController {

    jwtAuthenticatorFacade jwtAuthenticator;

    @PostMapping("/token")
    public ResponseEntity<JwtResponseDto> createToken(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        final JwtResponseDto jwtResponseDto = jwtAuthenticator.createToken(loginRequestDto);
        return ResponseEntity.ok(jwtResponseDto);
    }
}
