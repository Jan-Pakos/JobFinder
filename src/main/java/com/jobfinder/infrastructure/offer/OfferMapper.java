package com.jobfinder.infrastructure.offer;

import com.jobfinder.domain.offer.dto.OfferDto;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import com.jobfinder.infrastructure.offer.dto.AllOffersResponseDto;
import com.jobfinder.infrastructure.offer.dto.OfferPatchRequestDto;
import com.jobfinder.infrastructure.offer.dto.OfferRequestDto;

import java.util.List;

class OfferMapper {

    public static AllOffersResponseDto mapFromListOfOfferResponseDtoToAllOffersResponseDto(List<OfferResponseDto> offers) {
        return new AllOffersResponseDto(offers);
    }

    public static OfferDto mapOfferPatchRequestDtoToOffer(OfferPatchRequestDto offerRequestDto) {
        return OfferDto.builder()
                .title(offerRequestDto.title())
                .company(offerRequestDto.company())
                .salary(offerRequestDto.salary())
                .offerUrl(offerRequestDto.offerUrl())
                .build();
    }

    public static OfferDto mapOfferRequestDtoToOffer(OfferRequestDto offerRequestDto) {
        return OfferDto.builder()
                .title(offerRequestDto.title())
                .company(offerRequestDto.company())
                .salary(offerRequestDto.salary())
                .offerUrl(offerRequestDto.offerUrl())
                .build();
    }


}
