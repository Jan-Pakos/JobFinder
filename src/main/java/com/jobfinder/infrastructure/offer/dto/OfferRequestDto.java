package com.jobfinder.infrastructure.offer.dto;

public record OfferRequestDto(
        String title,
        String company,
        String salary,
        String offerUrl
){
}
