package com.jobfinder.domain.offer;

import java.util.List;

public class OfferSavingException extends RuntimeException {
    public OfferSavingException(String message, List<Offer> fetchedOffers) {
        super(String.format("error" + message + fetchedOffers.toString()));
    }
}
