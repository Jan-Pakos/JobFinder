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
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        assertThat(newOffers.size()).isEqualTo(0);


        // 3. User tried to get a JWT token by making POST request to /token and the system returned UNAUTHORIZED 401
        // 4. User made a GET request to /offers with no JWT token and the system returned UNAUTHORIZED 401
        // given
        String urlPath = "/offers";
        // when
        ResultActions perform = mockMvc.perform(get(urlPath).contentType(MediaType.APPLICATION_JSON.getMediaType()));
        // then
        perform.andExpect(status().isOk());

        // 5. The user made a POST request to /register with username=user1 and password=password1 and the system returned OK 200 and JWTtoken=AAAA.BBBB.CCC

        ResultActions userRegistration = mockMvc.perform(post("/offers").content(
                """
                        {
                            "username": "user1",
                            "password": "password1"
                        }
                        
                        """.trim()).contentType("application/json"));

        // 6. There are 2 new offers on the external HTTP server
        // given && when && then
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithTwoJobOffers())));

        // 7. the scheduler ran a 2nd time and made a GET request to the external server and the system added 2 new offers with ids: 1000 and 2000 to the database

        // given && when
        List<OfferResponseDto> twoNewOffers = offerFetchable.getNewOffers();

        // then
        assertThat(twoNewOffers.size()).isEqualTo(2);
        // 8. User made a GET to /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and the system returned OK 200 with 2 offers with ids: 1000 and 2000

        // 9. User made GET request to /offers and 2 job offers are returned with ids: 1000 and 2000

        // 10. User made a GET request to /offers/9999 and the system returned NOT_FOUND 404 with message “Offer with id 9999 not found”

        // given & when
        ResultActions performGetOfferWithExistingId = mockMvc.perform(get("/offers/9999")
                .contentType(MediaType.APPLICATION_JSON.getMediaType()));

        // then
        performGetOfferWithExistingId.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Offer with id 9999 not found"));

        // 11. User made GET request to /offers/1000 and the system returned OK 200 with offer with id 1000

        // given & when
        ResultActions performGetOfferWithId1000 = mockMvc.perform(get("/offers/1000")
                .contentType(MediaType.APPLICATION_JSON.getMediaType()));

        // then
        performGetOfferWithExistingId.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1000"));


//        12. scheduler ran a 3rd time and made a GET request to the external server and the system added 2 new offers with ids: 3000 and 4000 to the database

        // given
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithFourJobOffers())));

        // when
        List<OfferResponseDto> twoNewOffersWithIds3000and4000 = offerFetchable.getNewOffers();
        // then
        assertThat(twoNewOffersWithIds3000and4000.size()).isEqualTo(2);

        // 13. User made a POST request with header “Authorization: Bearer AAAA.BBBB.CCC” and JSON body with offer details to /offers and the system returned OK 200 with the offer with id: 5000
        mockMvc.perform(post("/offers").content(
                        """
                                {
                                    "title": "DevOps Engineer",
                                    "company": "Company Y",
                                    "salary": "120K",
                                    "offerUrl": "www.offer.com/5"
                                }
                                
                                """.trim()).contentType("application/json").header("Authorization", "Bearer AAAA.BBBB.CCC"))
                .andExpect(status().isCreated());
//        13. The user made a GET request to /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and the system returned OK 200 with 4 offers with ids: 1000, 2000, 3000, 4000
    }
}
