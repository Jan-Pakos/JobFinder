package com.jobfinder.domain.offer;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class OfferService {

    private final OfferRepository offerRepository;
    private final OfferFetchable offerFetcher;

    List<Offer> fetchNewOffersNotInDb() {
        List<Offer> fetchedOffers = fetchOffers();
        List<Offer> newOffers = filterNotExistingOffers(fetchedOffers);
        newOffers.forEach(offerRepository::save);
        return newOffers;

    }

    Offer save(Offer offer) {
        if(offerExistsByUrl(offer.url())) {
            throw new OfferDuplicateException("Offer with url " + offer.url() + " already exists");
        }
        return offerRepository.save(offer);
    }

    private boolean offerExistsByUrl(String url) {
        return offerRepository.existsByUrl(url);
    }

    private List<Offer> fetchOffers() {
        return offerFetcher.getNewOffers().stream().map(
                OfferMapper::mapFromOfferResponseToOffer
        ).toList();
    }

    private List<Offer> filterNotExistingOffers(List<Offer> jobOffers) {
        return jobOffers.stream()
                .filter(offerDto -> !offerDto.url().isEmpty())
                .filter(offerDto -> !offerRepository.existsByUrl(offerDto.url()))
                .toList();
    }

}
