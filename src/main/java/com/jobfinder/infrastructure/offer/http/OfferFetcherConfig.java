package com.jobfinder.infrastructure.offer.http;

import com.jobfinder.domain.offer.OfferFetchable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class OfferFetcherConfig {

    @Bean
    public OfferFetchable remoteOfferClient(
            @Value("${offer.http.client.uri}") String uri,
            @Value("${offer.http.client.port}") int port,
            @Value("${offer.http.client.connectionTimeout}") int connectionTimeout,
            @Value("${offer.http.client.readTimeout}") int readTimeout) {
        return new OffersHttpClient(buildRestClient(uri, port, connectionTimeout, readTimeout));
    }

    protected RestClient buildRestClient(String uri, int port, int connectionTimeout, int readTimeout) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
        return RestClient.builder()
                .baseUrl(uri + ":" + port)
                .requestFactory(factory)
                .build();
    }
}
