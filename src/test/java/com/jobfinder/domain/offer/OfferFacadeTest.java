package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferDto;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OfferFacadeTest {

    List<OfferResponseDto> offers = List.of(
            OfferResponseDto.builder()
                    .title("Junior Java Developer")
                    .company("Tech Solutions")
                    .salary("$80,000 - $120,000")
                    .offerUrl("https://someurl/1")
                    .build(),
            OfferResponseDto.builder()
                    .title("Senior Java Developer")
                    .company("Innovatech")
                    .salary("$120,000 - $160,000")
                    .offerUrl("https://someurl/2")
                    .build()
    );

    @Test
    public void should_fetch_and_save_two_new_offers_when_theyre_not_in_db() {
        //given
        OfferFacade offerFacade =  new OfferFacadeTestConfiguration(offers).offerFacadeForTests();
        //when
        offerFacade.fetchNewOffersNotInDb();
        //then
        List<OfferResponseDto> allOffers = offerFacade.findAllOffers();
        assertEquals(2, allOffers.size());
    }

    @Test
    public void should_throw_exception_when_offer_not_found() {
        //given
        OfferFacade offerFacade =  new OfferFacadeTestConfiguration(offers).offerFacadeForTests();
        OfferDto offerDto1 = OfferDto.builder()
                .title("Java Developer")
                .company("Tech Solutions")
                .salary("$80,000 - $120,000")
                .offerUrl("https://techsolutions.com/careers/java-developer/123")
                .build();
        offerFacade.saveOffer(offerDto1);
        //when
        String invalidId = "INVALID_ID";
        Exception exception = assertThrows(OfferNotFoundException.class, () -> {
            offerFacade.findOfferById(invalidId);
        });
        // then
        String expectedMessage = "Offer with id " + invalidId + " not found";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void should_save_new_offer_when_it_isnt_in_database() {
        // given
        OfferFacade offerFacade =  new OfferFacadeTestConfiguration(offers).offerFacadeForTests();
        OfferDto offerDto1 = OfferDto.builder()
                .title("Java Developer")
                .company("Tech Solutions")
                .salary("$80,000 - $120,000")
                .offerUrl("https://techsolutions.com/careers/java-developer/123")
                .build();
        offerFacade.saveOffer(offerDto1);
        // when
        OfferDto offerDto2 = OfferDto.builder()
                .title("Junior Java Developer")
                .company("Tech Solutions")
                .salary("$80,000 - $120,000")
                .offerUrl("https://someurl/3")
                .build();
        Set<OfferDto> allOffers = Set.of(offerDto2);
        offerFacade.fetchNewOffersNotInDb();
        // then
        List<OfferResponseDto> allOffers1 = offerFacade.findAllOffers();
        assertEquals(3, allOffers1.size());
    }

    @Test
    public void should_not_save_new_offer_when_it_already_is_in_database() {
        OfferFacade offerFacade =  new OfferFacadeTestConfiguration(offers).offerFacadeForTests();
        // given
        OfferDto offerDto1 = OfferDto.builder()
                .title("Java Developer")
                .company("Tech Solutions")
                .salary("$80,000 - $120,000")
                .offerUrl("https://techsolutions.com/careers/java-developer/123")
                .build();
        offerFacade.saveOffer(offerDto1);
        // when

        // then
    }



}