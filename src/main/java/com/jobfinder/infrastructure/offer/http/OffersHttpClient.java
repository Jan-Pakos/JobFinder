package com.jobfinder.infrastructure.offer.http;

import com.jobfinder.domain.offer.OfferFetchable;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
class OffersHttpClient implements OfferFetchable {

    private final WebClient webClient;

    @Override
    public List<OfferResponseDto> getNewOffers() {
        try {
            List<OfferResponseDto> offers = webClient
                    .get()
                    .uri("/offers")
                    .retrieve()
                    .bodyToFlux(OfferResponseDto.class)
                    .collectList()
                    .block();

            if (offers == null || offers.isEmpty()) {
                log.warn("No offers returned from API");
                return Collections.emptyList();
            }

            log.info("Successfully fetched {} offers", offers.size());
            return offers;

        } catch (WebClientResponseException e) {
            log.error("API responded with error: {} - {}",
                    e.getStatusCode(), e.getResponseBodyAsString());
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage());

        } catch (WebClientRequestException e) {
            log.error("Failed to connect to offers API: {}", e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Unable to connect to offers service"
            );
        }
    }
}
