package com.jobfinder.infrastructure.offer;

import com.jobfinder.domain.offer.OfferFacade;
import com.jobfinder.domain.offer.dto.OfferDto;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import com.jobfinder.infrastructure.offer.dto.AllOffersResponseDto;
import com.jobfinder.infrastructure.offer.dto.OfferRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/offers/{id}")
    public ResponseEntity<OfferResponseDto> putOffer(@PathVariable String id, @RequestBody @Valid OfferRequestDto offerDto) {
        OfferDto offerDto1 = OfferMapper.mapOfferRequestDtoToOffer(offerDto);
        OfferResponseDto offerResponseDto = offerFacade.updateWholeOffer(offerDto1, id);
        return ResponseEntity.ok(offerResponseDto);
    }

    @PatchMapping("/offers/{id}")
    public ResponseEntity<String> patchOffer(@PathVariable String id, @RequestBody @Valid OfferRequestDto offerRequestDto) {
        OfferDto offerDto1 = OfferMapper.mapOfferRequestDtoToOffer(offerRequestDto);
        offerFacade.partiallyUpdateOffer(offerDto1,id);
        return ResponseEntity.ok("Patch request to /offers/{id}");
    }
}
