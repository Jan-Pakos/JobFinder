package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferResponseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Configuration
class OfferFacadeConfiguration {

    @Bean
    public OfferFacade offerFacade(OfferRepository offerRepository, OfferService offerService) {
        return new OfferFacade(offerRepository, offerService);
    }

    @Bean
    public OfferService offerService(OfferFetchable offerFetchable, OfferRepository offerRepository) {
        return new OfferService(offerRepository, offerFetchable);
    }

//    @Bean
//    public OfferFetchable offerFetchable() {
//        return List::of;
//    }
}
