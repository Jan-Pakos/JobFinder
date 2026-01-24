package com.jobfinder.infrastructure.offer.http;

import com.jobfinder.domain.offer.OfferFetchable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class OfferFetcherRestTemplateConfig {

    @Bean
    public RestTemplateErrorHandler restTemplateResponseErrorHandler() {
        return new RestTemplateErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(@Value("${offer.fetcher.rest.template.config.connectionTimeout}") long connectionTimeout,
                                     @Value("${offer.fetcher.rest.template.config.readTimeout}") long readTimeout,
                                     RestTemplateErrorHandler restTemplateResponseErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .connectTimeout(Duration.ofMillis(connectionTimeout))
                .readTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public OfferFetchable remoteOfferClient(RestTemplate restTemplate,
                                            @Value("${offer.fetcher.rest.template.config.uri}") String uri,
                                            @Value("${offer.fetcher.rest.template.config.port}") int port) {
        return new OffersHttpClient(restTemplate, uri, port);
    }
}
