package com.jobfinder.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.jobfinder.BaseIntegrationTest;
import com.jobfinder.domain.offer.OfferFetchable;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import com.jobfinder.infrastructure.offer.scheduler.OffersScheduler;
import io.micrometer.observation.Observation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.util.List;

import static org.awaitility.Awaitility.await;

class UserFetchedNewJobsIntegrationTest extends BaseIntegrationTest implements SampleJobOffersJsonBodies {

    @Autowired
    private OfferFetchable offerFetchable;

    @Autowired
    OffersScheduler offersScheduler;

    @Test
    void userFetchedNewJobs() {
        // 1. There are no job offers in the external HTTP server

        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithNoJobOffers())));

        List<OfferResponseDto> newOffers = offerFetchable.getNewOffers();
        assert newOffers.isEmpty();
        // 2. Scheduler ran 1st time and made GET request to external server and the system added 0 offers to the database
        offersScheduler.fetchNewOffers();
        // 3. User tried to get a JWT token by making POST request to /token and the system returned UNAUTHORIZED 401
        // 4. User made a GET request to /offers with no JWT token and the system returned UNAUTHORIZED 401
    }
}
