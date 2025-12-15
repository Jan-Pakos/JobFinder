package com.jobfinder.infrastructure.offer.dto;

import com.jobfinder.domain.offer.dto.OfferDto;

import java.util.List;

public record AllOffersResponseDto(
        List<OfferDto> offers
) {
}
