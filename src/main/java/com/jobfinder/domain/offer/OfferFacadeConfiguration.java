package com.jobfinder.domain.offer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OfferFacadeConfiguration {

    @Bean
    public OfferFacade offerFacade(OfferRepository offerRepository, OfferService offerService, OfferUpdater offerUpdater) {
        return new OfferFacade(offerRepository, offerService, offerUpdater);
    }

    @Bean
    public OfferService offerService(OfferFetchable offerFetchable, OfferRepository offerRepository) {
        return new OfferService(offerRepository, offerFetchable);
    }
}
