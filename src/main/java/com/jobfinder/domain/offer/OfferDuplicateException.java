package com.jobfinder.domain.offer;

public class OfferDuplicateException extends RuntimeException {
    public OfferDuplicateException(String message) {
        super(message);
    }
}
