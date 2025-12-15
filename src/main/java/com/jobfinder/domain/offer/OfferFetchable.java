package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferResponseDto;

import java.util.List;

public interface OfferFetchable {

    List<OfferResponseDto> getNewOffers();
}
