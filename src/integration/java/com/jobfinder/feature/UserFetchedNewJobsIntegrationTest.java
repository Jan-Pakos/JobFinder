package com.jobfinder.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.jobfinder.BaseIntegrationTest;
import com.jobfinder.domain.login.dto.RegistrationResultDto;
import com.jobfinder.domain.offer.OfferFetchable;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import com.jobfinder.infrastructure.loginandregister.controller.dto.JwtResponseDto;
import com.jobfinder.infrastructure.offer.scheduler.OffersScheduler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        // given && when
        ResultActions userPostRequest = mockMvc.perform(post("/token").content(
                """
                        {
                            "username": "user1",
                            "password": "password1"
                        }
                        
                        """.trim()).contentType(MediaType.APPLICATION_JSON.getMediaType())
        );
        // then
        userPostRequest.andExpect(status().isUnauthorized())
                .andExpect(content().json(
                        """
                                {
                                    "message": "Bad Credentials",
                                    "status": "UNAUTHORIZED"
                                }
                                
                                """.trim()));

        // 4. User made a GET request to /offers with no JWT token and the system returned UNAUTHORIZED 401
        // given
        String urlPath = "/offers";
        // when
        ResultActions perform = mockMvc.perform(get(urlPath)
                .contentType(MediaType.APPLICATION_JSON.getMediaType()));
        // then
        perform.andExpect(status().isForbidden());

        // 5. The user made a POST request to /register with username=user1 and password=password1 and the system returned CREATED 201 and JWTtoken=AAAA.BBBB.CCC

        // given && when
        ResultActions userRegisterRequest = mockMvc.perform(post("/register").content(
                        """
                                {
                                    "username": "user1",
                                    "password": "password1"
                                }
                                
                                """.trim()).contentType(MediaType.APPLICATION_JSON.getMediaType()));

        // then
        userRegisterRequest.andExpect(status().isCreated());
        String responseBody = userRegisterRequest.andReturn().getResponse().getContentAsString();
        RegistrationResultDto registrationResultDto = objectMapper.readValue(responseBody, RegistrationResultDto.class);
        assertAll(
                () -> assertThat(registrationResultDto.username()).isEqualTo("user1"),
                () -> assertThat(registrationResultDto.id()).isNotNull(),
                () -> assertThat(registrationResultDto.password()).isNotNull()

        );

        // 6. User tried to get a JWT token by making POST request to /token with username=user1 and password=password1 and the system returned OK 200 with JWT token
        // given && when
        ResultActions secondUserRegisterRequest = mockMvc.perform(post("/register").content(
                """
                        {
                            "username": "user1",
                            "password": "password1"
                        }
                        
                        """.trim()).contentType(MediaType.APPLICATION_JSON.getMediaType()));
        // then
        ResultActions resultActions = secondUserRegisterRequest.andExpect(status().isOk());
        String secondResponseBody = secondUserRegisterRequest.andReturn().getResponse().getContentAsString();
        JwtResponseDto jwtResponseDto = objectMapper.readValue(secondResponseBody, JwtResponseDto.class);
        String token = jwtResponseDto.token();
        assertAll(
                () -> assertThat(jwtResponseDto.username()).isEqualTo("user1"),
                () -> assertThat(token).matches(Pattern.compile("^([A-Za-z0-9-_=]+\\.)+([A-Za-z0-9-_=])+\\.?$"))
        );

        // 7. There are 2 new offers on the external HTTP server (4 in total)
        // given && when && then
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithTwoJobOffers())));

        // 8. the scheduler ran a 2nd time and made a GET request to the external server and the system added 2 new offers with ids: 1000 and 2000 to the database

        // given && when
        List<OfferResponseDto> twoNewOffers = offerFetchable.getNewOffers();

        // then
        assertThat(twoNewOffers.size()).isEqualTo(2);
        // 9. User made a GET to /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and the system returned OK 200 with 2 offers with ids: 1000 and 2000

        // given & when
        ResultActions performGetWhenTwoOffers = mockMvc.perform(get("/offers")
                .contentType(MediaType.APPLICATION_JSON.getMediaType()));
        // then
        performGetWhenTwoOffers.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("1000"))
                .andExpect(jsonPath("$[1].id").value("2000"));

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
        performGetOfferWithId1000.andExpect(status().isOk())
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
//        14. The user made a GET request to /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and the system returned OK 200 with 4 offers with ids: 1000, 2000, 3000, 4000
        // given & when
        ResultActions performGetFor4Offers = mockMvc.perform(get("/offers")
                .contentType(MediaType.APPLICATION_JSON.getMediaType()));
        // then
        performGetFor4Offers.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4));
    }
}
