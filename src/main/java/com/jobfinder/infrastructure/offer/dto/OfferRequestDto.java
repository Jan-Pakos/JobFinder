package com.jobfinder.infrastructure.offer.dto;

public record OfferRequestDto(
        String title,
        String description,
        String companyName,
        String location,
        String salaryRange,
        String url
){
}
