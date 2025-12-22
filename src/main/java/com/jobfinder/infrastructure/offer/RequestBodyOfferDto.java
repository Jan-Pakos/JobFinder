package com.jobfinder.infrastructure.offer;

public record RequestBodyOfferDto(
        String title,
        String company,
        String salary,
        String offerUrl
) {
}
