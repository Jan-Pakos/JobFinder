package com.jobfinder.domain.offer.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record OfferResponseDto(
        String id,
        String title,
        String company,
        String salary,
        String offerUrl
) implements Serializable {
}
