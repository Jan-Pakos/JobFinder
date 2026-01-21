package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferDto;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.*;

@AllArgsConstructor
public class OfferFacade {

    private final OfferRepository offerRepository;
    private final OfferService offerService;
    private final OfferUpdater offerUpdater;

    @CacheEvict(value = "allJobOffers", allEntries = true)
    public OfferResponseDto saveOffer(OfferDto dto) {
        Offer offer = OfferMapper.mapOfferDtoToOffer(dto);
        Offer save = offerService.save(offer);
        OfferResponseDto offerResponseDto = OfferMapper.mapOfferToOfferResponseDto(save);
        return offerResponseDto;
    }

    @Cacheable("allJobOffers")
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

    @CacheEvict(value = "allJobOffers", allEntries = true)
    public List<OfferResponseDto> fetchAndSaveOffers() {
        return offerService.fetchNewOffersNotInDb().stream()
                .map(OfferMapper::mapOfferToOfferResponseDto).toList();
    }

    @CacheEvict(value = "allJobOffers", allEntries = true)
    public OfferResponseDto partiallyUpdateOffer(OfferDto offerDto, String id) {
        return offerUpdater.partiallyUpdateOffer(offerDto, id);
    }

    @CacheEvict(value = "allJobOffers", allEntries = true)
    public OfferResponseDto updateWholeOffer(OfferDto offerDto, String id) {
        return new OfferResponseDto("Not implemented yet", null, null, null, null);
    }
}
