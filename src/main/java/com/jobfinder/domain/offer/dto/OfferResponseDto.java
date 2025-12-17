package com.jobfinder.domain.offer.dto;

import lombok.Builder;

@Builder
public record OfferResponseDto(
        String title,
        String company,
        String salary,
        String offerUrl
) {
}
