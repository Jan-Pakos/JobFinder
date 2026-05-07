package com.jobfinder.infrastructure.offer.http;

import com.jobfinder.domain.offer.OfferFetchable;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@Log4j2
public class OffersHttpClient implements OfferFetchable {

    private final WebClient webClient;

    @Override
    public List<OfferResponseDto> getNewOffers() {
        log.info("Started fetching offers using http client");
        try {
            return webClient.get()
                    .uri("/offers")
                    .retrieve()
                    .onStatus(status -> status.value() == 204,
                            response -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)))
                    .onStatus(status -> status.value() == 401,
                            response -> Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)))
                    .onStatus(status -> status.value() == 404,
                            response -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)))
                    .bodyToMono(new ParameterizedTypeReference<List<OfferResponseDto>>() {})
                    .block();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (WebClientRequestException e) {
            log.error("Error while fetching offers using http client: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
