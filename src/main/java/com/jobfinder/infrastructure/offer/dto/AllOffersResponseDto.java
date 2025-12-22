package com.jobfinder.infrastructure.offer.dto;

import com.jobfinder.domain.offer.dto.OfferResponseDto;

import java.util.List;

public record AllOffersResponseDto(
        List<OfferResponseDto> offers
) {
}
