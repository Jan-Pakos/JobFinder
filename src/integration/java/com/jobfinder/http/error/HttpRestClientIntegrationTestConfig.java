package com.jobfinder.http.error;

import com.jobfinder.domain.offer.OfferFetchable;
import com.jobfinder.infrastructure.offer.http.OfferFetcherConfig;
import com.jobfinder.infrastructure.offer.http.OffersHttpClient;

import static com.jobfinder.BaseIntegrationTest.WIRE_MOCK_HOST;

class HttpRestClientIntegrationTestConfig extends OfferFetcherConfig {

    public OfferFetchable remoteOfferTest(int port, int connectionTimeout, int readTimeout) {
        return new OffersHttpClient(buildRestClient(WIRE_MOCK_HOST, port, connectionTimeout, readTimeout));
    }
}
