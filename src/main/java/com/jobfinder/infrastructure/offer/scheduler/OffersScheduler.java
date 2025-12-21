package com.jobfinder.infrastructure.offer.scheduler;

import com.jobfinder.domain.offer.OfferFacade;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
public class OffersScheduler {

    private final OfferFacade offerFacade;
    private static final String STARTED_MESSAGE = "Scheduler STARTED fetching new offers from external server started";
    private static final String FINISHED_MESSAGE = "Scheduler FINISHED fetching new offers from external server started";
    private static final String HOW_MANY_NEW_OFFERS_MESSAGE = "Added {} new offers to the database";

    @Scheduled(fixedDelayString = "${offer.fetcher.scheduler.fixedDelay}")
    public List<OfferResponseDto> fetchNewOffers() {
        log.info(STARTED_MESSAGE + " at " + new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis()));
        List<OfferResponseDto> offerResponseDtos = offerFacade.fetchNewOffersNotInDb();
        log.info(FINISHED_MESSAGE + " at " + new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis()));
        log.info(HOW_MANY_NEW_OFFERS_MESSAGE, offerResponseDtos.size());
        return offerResponseDtos;
    }

}
