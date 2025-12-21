package com.jobfinder.infrastructure.offer.http;

import com.jobfinder.domain.offer.OfferFetchable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
class OfferFetcherRestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(
            @Value("${offer.fetcher.rest.template.config.connectionTimeout}") long connectionTimeout,
            @Value("${offer.fetcher.rest.template.config.readTimeout}") long readTimeout) {
        return new RestTemplateBuilder()
                .requestFactory(SimpleClientHttpRequestFactory.class)
                .connectTimeout(Duration.ofMillis(connectionTimeout))
                .readTimeout(Duration.ofMillis(readTimeout))
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }

@Bean("offerFetchableRestTemplate")
    public OfferFetchable offerFetchable(
            RestTemplate restTemplate,
            @Value("${offer.fetcher.rest.template.config.uri}") String baseUrl,
            @Value("${offer.fetcher.rest.template.config.port}") String port) {
        return new OffersHttpClient(restTemplate, baseUrl, Integer.parseInt(port));
    }
}
