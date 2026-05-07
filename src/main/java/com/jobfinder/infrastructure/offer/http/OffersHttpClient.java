package com.jobfinder.infrastructure.offer.http;

import com.jobfinder.domain.offer.OfferFetchable;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Log4j2
public class OffersHttpClient implements OfferFetchable {

    private final RestClient restClient;

    @Override
    public List<OfferResponseDto> getNewOffers() {
        log.info("Started fetching offers using http client");
        try {
            return restClient.get()
                    .uri("/offers")
                    .retrieve()
                    .onStatus(status -> status.value() == 204,
                            (req, res) -> { throw new ResponseStatusException(HttpStatus.NO_CONTENT); })
                    .onStatus(status -> status.value() == 401,
                            (req, res) -> { throw new ResponseStatusException(HttpStatus.UNAUTHORIZED); })
                    .onStatus(status -> status.value() == 404,
                            (req, res) -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); })
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            (req, res) -> { throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR); })
                    .body(new ParameterizedTypeReference<List<OfferResponseDto>>() {});
        } catch (ResponseStatusException e) {
            throw e;
        } catch (ResourceAccessException e) {
            log.error("Error while fetching offers using http client: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RestClientException e) {
            log.error("Error while fetching offers using http client: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
