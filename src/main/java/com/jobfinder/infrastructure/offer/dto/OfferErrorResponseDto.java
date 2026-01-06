package com.jobfinder.infrastructure.offer.dto;

import org.springframework.http.HttpStatus;

public record OfferErrorResponseDto(
        String message,
        HttpStatus status
) {
}
