package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferResponseDto;

import java.util.List;

public class OfferFacadeTestConfiguration {

    private final InMemoryFetcherTestImpl fetcher;
    private final InMemoryOfferRepository repository;

    public OfferFacadeTestConfiguration(List<OfferResponseDto> remoteServerOffers) {
        this.fetcher = new InMemoryFetcherTestImpl(remoteServerOffers);
        this.repository = new InMemoryOfferRepository();
    }

    OfferFacade offerFacadeForTests() {
        return new OfferFacade(repository, new OfferService( repository, fetcher));
    }

}
