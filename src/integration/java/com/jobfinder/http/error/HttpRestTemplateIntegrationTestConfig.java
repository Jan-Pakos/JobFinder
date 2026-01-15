package com.jobfinder.http.error;

import com.jobfinder.domain.offer.OfferFetchable;
import com.jobfinder.infrastructure.offer.http.OfferFetcherRestTemplateConfig;
import org.springframework.web.client.RestTemplate;

import static com.jobfinder.BaseIntegrationTest.WIRE_MOCK_HOST;

class HttpRestTemplateIntegrationTestConfig extends OfferFetcherRestTemplateConfig {

    public OfferFetchable remoteOfferTest(int port, int connectionTimeout, int readTimeout) {
        final RestTemplate restTemplate = restTemplate(connectionTimeout, readTimeout, restTemplateErrorHandler());
        return offerFetchable(restTemplate, WIRE_MOCK_HOST, String.valueOf(port));
    }
}
