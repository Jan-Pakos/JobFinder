package com.jobfinder.infrastructure.offer.dto;

import jakarta.validation.constraints.Size;

public record OfferPatchRequestDto(
        @Size(min = 1, message = "Title cannot be empty if provided")
        String title,

        @Size(min = 1, message = "Company cannot be empty if provided")
        String company,

        @Size(min = 1, message = "Salary cannot be empty if provided")
        String salary,

        @Size(min = 1, message = "URL cannot be empty if provided")
        String offerUrl
) {
}
