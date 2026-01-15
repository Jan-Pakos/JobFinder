package com.jobfinder.infrastructure.loginandregister.controller.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record TokenErrorResponseDto(
        String message,
        HttpStatus status
) {
}
