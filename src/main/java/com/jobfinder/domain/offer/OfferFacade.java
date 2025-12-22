package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferDto;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class OfferFacade {

    private final OfferRepository offerRepository;
    private final OfferService offerService;

    public OfferDto saveOffer(OfferDto dto) {
        Offer offer = OfferMapper.mapOfferDtoToOffer(dto);
        Offer savedOffer = offerRepository.save(offer);
        return OfferMapper.mapOfferToOfferDto(savedOffer);
    }

    public List<OfferResponseDto> findAllOffers() {
        List<Offer> list = offerRepository.findAll().stream().toList();
        return list.stream()
                .map(OfferMapper::mapOfferToOfferResponseDto)
                .toList();
    }

    public OfferResponseDto findOfferById(String id) {
        Offer offer = offerRepository.findById(id).orElseThrow(
                () -> new OfferNotFoundException("Offer with id " + id + " not found")
        );
        return OfferMapper.mapOfferToOfferResponseDto(offer);
    }

    public List<OfferResponseDto> fetchNewOffersNotInDb() {
        return offerService.fetchNewOffersNotInDb().stream()
                .map(OfferMapper::mapOfferToOfferResponseDto).toList();
    }
}
