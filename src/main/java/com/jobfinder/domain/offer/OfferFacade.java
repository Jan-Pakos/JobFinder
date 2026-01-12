package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferDto;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class OfferFacade {

    private final OfferRepository offerRepository;
    private final OfferService offerService;
    private final OfferUpdater offerUpdater;

    public OfferResponseDto saveOffer(OfferDto dto) {
        Offer offer = OfferMapper.mapOfferDtoToOffer(dto);
        Offer save = offerService.save(offer);
        OfferResponseDto offerResponseDto = OfferMapper.mapOfferToOfferResponseDto(save);
        return offerResponseDto;
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

    public OfferResponseDto partiallyUpdateOffer(OfferDto offerDto, String id) {
        return offerUpdater.partiallyUpdateOffer(offerDto, id);
    }

    public OfferResponseDto updateWholeOffer(OfferDto offerDto, String id) {
        return new OfferResponseDto("Not implemented yet", null, null, null, null);
    }
}
