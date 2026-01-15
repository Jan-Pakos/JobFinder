package com.jobfinder.infrastructure.security.jwt;


import com.jobfinder.infrastructure.loginandregister.controller.dto.JwtResponseDto;
import com.jobfinder.infrastructure.loginandregister.controller.dto.LoginRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class jwtAuthenticatorFacade {

    private final AuthenticationManager authenticationManager;

    public JwtResponseDto createToken(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.username(),
                        loginRequestDto.password()
                )
        );
        return JwtResponseDto.builder()
                .username(authentication.getName()).token(authentication.getCredentials().toString()).build();
    }
}
