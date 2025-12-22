package com.jobfinder.infrastructure.offer;

import com.jobfinder.domain.offer.OfferFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OfferRestController {

    private final OfferFacade offerFacade;

    @GetMapping("/offers")
    public ResponseEntity<String> getAllOffers() {
        return ResponseEntity.ok("Hello from OfferRestController");
    }

    @PostMapping("/offers")
    public ResponseEntity<String> postOffer(@RequestBody RequestBodyOfferDto offerDto) {
        return ResponseEntity.ok("Post request to /offers");
    }
}
