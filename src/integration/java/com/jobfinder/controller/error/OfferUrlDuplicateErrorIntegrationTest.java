package com.jobfinder.controller.error;

import com.jobfinder.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OfferUrlDuplicateErrorIntegrationTest extends BaseIntegrationTest {

    @Test
    public void should_return_409_conflict_when_adding_offer_with_existing_url() throws Exception {
        // step 1
        // given & when
        ResultActions firstRequest = mockMvc.perform(post("/offers")
                .content("""
                        {
                            "title": "Software Engineer",
                            "company": "Tech Corp",
                            "salary": "100000",
                            "offerUrl": "http://techcorp.com/jobs/123"
                        }
                        """).contentType(MediaType.APPLICATION_JSON.getMediaType()));
        // then
        firstRequest.andExpect(status().isCreated());

        // step 2
        // given & when
        ResultActions requestWithAlreadyExistingOfferUrl = mockMvc.perform(post("/offers")
                .content("""
                        {
                            "title": "Software Engineer",
                            "company": "Tech Corp",
                            "salary": "100000",
                            "offerUrl": "http://techcorp.com/jobs/123"
                        }
                        """).contentType(MediaType.APPLICATION_JSON.getMediaType()));
        // then
        requestWithAlreadyExistingOfferUrl.andExpect(status().isConflict());
    }
}
