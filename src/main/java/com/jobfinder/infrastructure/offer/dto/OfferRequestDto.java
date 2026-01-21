package com.jobfinder.infrastructure.offer.dto;

import jakarta.validation.constraints.NotBlank;

public record OfferRequestDto(
        @NotBlank(message = "You must provide a title")
        String title,

        @NotBlank(message = "You must provide a company name")
        String company,

        @NotBlank(message = "You must provide a salary range")
        String salary,

        @NotBlank(message = "You must provide an url")
        String offerUrl
) {
}
