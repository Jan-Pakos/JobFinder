package com.jobfinder.controller.error;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.jobfinder.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;
import org.testcontainers.utility.DockerImageName;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OfferUrlDuplicateErrorIntegrationTest extends BaseIntegrationTest {

    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:6.0.5"));

    @RegisterExtension
    public static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort()).build();

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("offer.fetcher.rest.template.config.uri", () -> WIRE_MOCK_HOST);
        registry.add("offer.fetcher.rest.template.config.port", () -> wireMockServer.getPort());
    }

    @Test
    @WithMockUser
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
