package com.jobfinder.domain.offer.dto;

import lombok.Builder;

@Builder
public record OfferResponseDto(
        String id,
        String title,
        String company,
        String salary,
        String offerUrl
) {
}
