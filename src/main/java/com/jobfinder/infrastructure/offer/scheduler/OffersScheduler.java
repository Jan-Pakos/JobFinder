package com.jobfinder.infrastructure.offer.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
class OffersScheduler {

//    private final OfferFacade offerFacade;

    @Scheduled(cron = "*/5 * * * * *")
    public void fetchNewOffers() {
//        offerFacade.fetchNewOffersNotInDb();
        log.info("Scheduler jRunning");
    }

}
