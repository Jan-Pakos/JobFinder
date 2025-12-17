package com.jobfinder.infrastructure.offer.http;

import com.jobfinder.domain.offer.OfferFetchable;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Log4j2
class OffersHttpClient implements OfferFetchable {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final int port;


    @Override
    public List<OfferResponseDto> getNewOffers() {
        log.info("Fetching offers from external server");

        try {
            String url = buildUrl();
            HttpEntity<Void> entity = createHttpEntity();

            ResponseEntity<List<OfferResponseDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {
                    }
            );

            List<OfferResponseDto> offers = response.getBody();
            return offers != null ? offers : Collections.emptyList();
        } catch (RestClientException e) {
            log.error("Error fetching offers: " + e.getMessage());
            throw e;
        }
    }

    private String buildUrl() {
        return UriComponentsBuilder
                .fromUriString(baseUrl)
                .port(port)
                .path("/offers")
                .build()
                .toUriString();
    }

    private HttpEntity<Void> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(headers);
    }
}
