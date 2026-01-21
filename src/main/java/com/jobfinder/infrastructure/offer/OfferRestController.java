package com.jobfinder.infrastructure.offer;

import com.jobfinder.domain.offer.OfferFacade;
import com.jobfinder.domain.offer.dto.OfferDto;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import com.jobfinder.infrastructure.offer.dto.AllOffersResponseDto;
import com.jobfinder.infrastructure.offer.dto.OfferPatchRequestDto;
import com.jobfinder.infrastructure.offer.dto.OfferRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class OfferRestController {

    private final OfferFacade offerFacade;

    @GetMapping("/offers")
    public ResponseEntity<AllOffersResponseDto> getAllOffers() {
        List<OfferResponseDto> allOffers = offerFacade.findAllOffers();
        AllOffersResponseDto allOffersResponseDto = OfferMapper.mapFromListOfOfferResponseDtoToAllOffersResponseDto(allOffers);
        return ResponseEntity.ok(allOffersResponseDto);
    }

    @GetMapping("/offers/{id}")
    public ResponseEntity<OfferResponseDto> getOfferById(@PathVariable String id) {
        OfferResponseDto offerById = offerFacade.findOfferById(id);
        return ResponseEntity.ok(offerById);
    }

    @PostMapping("/offers")
    public ResponseEntity<OfferResponseDto> postOffer(
            @RequestBody(required = true) @Valid @NotNull OfferRequestDto offerDto) {
        OfferDto offerDto1 = OfferMapper.mapOfferRequestDtoToOffer(offerDto);
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(offerDto1);
        return ResponseEntity.status(HttpStatus.CREATED).body(offerResponseDto);
    }

    @PutMapping("/offers/{id}")
    public ResponseEntity<OfferResponseDto> putOffer(@PathVariable String id, @RequestBody @Valid OfferRequestDto offerDto) {
        OfferDto offerDto1 = OfferMapper.mapOfferRequestDtoToOffer(offerDto);
        OfferResponseDto offerResponseDto = offerFacade.updateWholeOffer(offerDto1, id);
        return ResponseEntity.ok(offerResponseDto);
    }

    @PatchMapping("/offers/{id}")
    public ResponseEntity<OfferResponseDto> patchOffer(@PathVariable String id, @RequestBody @Valid OfferPatchRequestDto offerRequestDto) {
        OfferDto offerDto1 = OfferMapper.mapOfferPatchRequestDtoToOffer(offerRequestDto);
        OfferResponseDto offerResponseDto = offerFacade.partiallyUpdateOffer(offerDto1, id);
        return ResponseEntity.ok(offerResponseDto);
    }
}
