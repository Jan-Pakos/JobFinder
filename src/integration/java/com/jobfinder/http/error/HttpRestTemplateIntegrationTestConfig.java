package com.jobfinder.http.error;

import com.jobfinder.domain.offer.OfferFetchable;
import com.jobfinder.infrastructure.offer.http.OfferFetcherConfig;
import org.springframework.web.client.RestTemplate;

import static com.jobfinder.BaseIntegrationTest.WIRE_MOCK_HOST;

class HttpRestTemplateIntegrationTestConfig extends OfferFetcherConfig {

    public OfferFetchable remoteOfferTest(int port, int connectionTimeout, int readTimeout) {
        final RestTemplate restTemplate = restTemplate(connectionTimeout, readTimeout, restTemplateResponseErrorHandler());
        return remoteOfferClient(restTemplate, WIRE_MOCK_HOST, port);
    }
}
