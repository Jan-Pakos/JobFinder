package com.jobfinder.domain.offer.dto;

import lombok.Builder;

@Builder
public record OfferDto(
        String title,
        String description,
        String companyName,
        String location,
        String salaryRange,
        String url
) {
}
