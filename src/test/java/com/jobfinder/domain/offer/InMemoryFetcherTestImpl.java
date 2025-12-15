package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferResponseDto;

import java.util.List;

public class InMemoryFetcherTestImpl implements OfferFetchable {

    List<OfferResponseDto> offers;

    InMemoryFetcherTestImpl(List<OfferResponseDto> offers) {
        this.offers = offers;
    }

    @Override
    public List<OfferResponseDto> getNewOffers() {
        return offers;
    }
}
