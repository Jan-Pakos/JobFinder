package com.jobfinder.infrastructure.offer;

import com.jobfinder.domain.offer.OfferFacade;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import com.jobfinder.infrastructure.offer.dto.AllOffersResponseDto;
import com.jobfinder.infrastructure.offer.dto.OfferRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OfferRestController {

    private final OfferFacade offerFacade;

    @GetMapping("/offers")
    public ResponseEntity<AllOffersResponseDto> getAllOffers() {
        List<OfferResponseDto> allOffers = offerFacade.findAllOffers();
        AllOffersResponseDto allOffersResponseDto = OfferMapper.mapFromListOfOfferResponseDtoToAllOffersResponseDto(allOffers);
        return ResponseEntity.ok(allOffersResponseDto);
    }

    @PostMapping("/offers")
    public ResponseEntity<String> postOffer(@RequestBody @Valid OfferRequestDto offerDto) {
        return ResponseEntity.ok("Post request to /offers");
    }
}
