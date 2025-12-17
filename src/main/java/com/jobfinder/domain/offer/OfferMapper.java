package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferDto;
import com.jobfinder.domain.offer.dto.OfferResponseDto;

public class OfferMapper {

    public static OfferDto mapOfferToOfferDto(Offer offer) {
        return OfferDto.builder()
                .title(offer.title())
                .description(offer.description())
                .companyName(offer.companyName())
                .location(offer.location())
                .salaryRange(offer.salaryRange())
                .url(offer.url())
                .build();
    }

    public static Offer mapOfferDtoToOffer(OfferDto offerDto) {
        return Offer.builder()
                .title(offerDto.title())
                .description(offerDto.description())
                .companyName(offerDto.companyName())
                .location(offerDto.location())
                .salaryRange(offerDto.salaryRange())
                .url(offerDto.url())
                .build();
    }

    public static OfferResponseDto mapOfferToOfferResponseDto(Offer offer) {
        return OfferResponseDto.builder()
                .title(offer.title())
                .company(offer.companyName())
                .salary(offer.salaryRange())
                .offerUrl(offer.url())
                .build();
    }

    public static Offer mapFromOfferResponseToOffer(OfferResponseDto offerResponseDto) {
        return Offer.builder()
                .title(offerResponseDto.title())
                .companyName(offerResponseDto.company())
                .salaryRange(offerResponseDto.salary())
                .url(offerResponseDto.offerUrl())
                .build();
    }
}
