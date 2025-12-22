package com.jobfinder.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.jobfinder.BaseIntegrationTest;
import com.jobfinder.domain.offer.OfferFetchable;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import com.jobfinder.infrastructure.offer.scheduler.OffersScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserFetchedNewJobsIntegrationTest extends BaseIntegrationTest implements SampleJobOffersJsonBodies {

    @Autowired
    private OfferFetchable offerFetchable;

    @Autowired
    OffersScheduler offersScheduler;

    @Test
    void userFetchedNewJobs() throws Exception {
        // 1. There are no job offers in the external HTTP server
        // given & when & then
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithNoJobOffers())));


        // 2. Scheduler ran 1st time and made GET request to external server and the system added 0 offers to the database
        // given & when
        List<OfferResponseDto> newOffers = offerFetchable.getNewOffers();
        // then
        assert newOffers.isEmpty();


        // 3. User tried to get a JWT token by making POST request to /token and the system returned UNAUTHORIZED 401
        // 4. User made a GET request to /offers with no JWT token and the system returned UNAUTHORIZED 401
        // given
        String url = "/offers";
        // when
        ResultActions perform = mockMvc.perform(get(url).contentType("application/json"));
        // then
        perform.andExpect(status().isOk());

//        5. The user made a POST request to /register with username=user1 and password=password1 and the system returned OK 200 and JWTtoken=AAAA.BBBB.CCC
//        6. User made a GET to /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and the system returned OK 200 with 2 offers with ids: 1000 and 2000

//        7. There are 2 new offers on the external HTTP server
//        8. the scheduler ran a 2nd time and made a GET request to the external server and the system added 2 new offers with ids: 1000 and 2000 to the database
//        9. User made GET request to /offers and 2 job offers are returned with ids: 1000 and 2000
//        10. User made a GET request to /offers/9999 and the system returned NOT_FOUND 404 with message “Offer with id 9999 not found”
//        11. User made GET request to /offers/1000 and the system returned OK 200 with offer with id 1000

//        ResultActions perform = mockMvc.perform(get("/offers").content(
//                """
//                        {
//                            "title": "Java Developer",
//                            "company": "Company X",
//                            "salary": "100K",
//                            "offerUrl": "www.offer.com/1"
//                        }
//
//                        """.trim()).contentType("application/json"));
//        12. scheduler ran a 3rd time and made a GET request to the external server and the system added 2 new offers with ids: 3000 and 4000 to the database
//        13. The user made a GET request to /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and the system returned OK 200 with 4 offers with ids: 1000, 2000, 3000, 4000
    }
}
