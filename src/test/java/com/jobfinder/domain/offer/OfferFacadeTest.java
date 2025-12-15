package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferDto;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OfferFacadeTest {


    @Test
    public void should_fetch_and_save_six_new_offers_when_theyre_not_in_db() {
        //given
        OfferFacade offerFacade =  new OfferFacadeTestConfiguration().offerFacadeForTests();
        //when
        offerFacade.fetchNewOffersNotInDb();
        //then
        List<OfferResponseDto> allOffers = offerFacade.findAllOffers();
        assertEquals(6, allOffers.size());
    }

    @Test
    public void should_display_offers_with_ids_1_and_2_when_those_are_saved_to_db() {
        //given
        OfferFacade offerFacade =  new OfferFacadeTestConfiguration().offerFacadeForTests();

        OfferDto offerDto1 = offerFacade.saveOffer(OfferDto.builder()
                .title("Java Developer")
                .description("Experienced Java Developer needed")
                .companyName("Tech Solutions")
                .location("New York, NY")
                .salaryRange("$80,000 - $120,000")
                .url("https://techsolutions.com/careers/java-developer/123")
                .build());
        OfferDto offerDto2 = offerFacade.saveOffer(OfferDto.builder()
                .title("Java Developer")
                .description("Experienced Java Developer needed")
                .companyName("Tech Solutions")
                .location("New York, NY")
                .salaryRange("$80,000 - $120,000")
                .url("https://techsolutions.com/careers/java-developer/124")
                .build());
        //when
        List<OfferResponseDto> allOffers = offerFacade.findAllOffers();
        //then
        assertEquals(2, allOffers.size());
        assertEquals(offerDto1.url(),allOffers.get(0).url());
        assertEquals(offerDto2.url(),allOffers.get(1).url());
    }

    @Test
    public void should_throw_exception_when_offer_not_found() {
        //given
        OfferFacade offerFacade =  new OfferFacadeTestConfiguration().offerFacadeForTests();
        OfferDto offerDto1 = offerFacade.saveOffer(OfferDto.builder()
                .title("Java Developer")
                .description("Experienced Java Developer needed")
                .companyName("Tech Solutions")
                .location("New York, NY")
                .salaryRange("$80,000 - $120,000")
                .url("https://techsolutions.com/careers/java-developer/123")
                .build());
        //when
        Long invalidId = 999L;
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
        OfferFacade offerFacade =  new OfferFacadeTestConfiguration().offerFacadeForTests();
        OfferDto offerDto1 = OfferDto.builder()
                .title("Java Developer")
                .description("Experienced Java Developer needed")
                .companyName("Tech Solutions")
                .location("New York, NY")
                .salaryRange("$80,000 - $120,000")
                .url("https://techsolutions.com/careers/java-developer/123")
                .build();
        offerFacade.saveOffer(offerDto1);
        // when
        OfferDto offerDto2 = OfferDto.builder()
                .title("Junior Java Developer")
                .description("No expectations")
                .companyName("Tech Solutions")
                .location("New York, NY")
                .salaryRange("$80,000 - $120,000")
                .url("https://techsolutions.com/careers/java-developer/124")
                .build();
        Set<OfferDto> allOffers = Set.of(offerDto2);
        offerFacade.fetchNewOffersNotInDb();
        // then
        List<OfferResponseDto> allOffers1 = offerFacade.findAllOffers();
        assertEquals(7, allOffers1.size());
    }

    @Test
    public void should_not_save_new_offer_when_it_already_is_in_database() {
        OfferFacade offerFacade =  new OfferFacadeTestConfiguration().offerFacadeForTests();
        // given
        OfferDto offerDto1 = OfferDto.builder()
                .title("Java Developer")
                .description("Experienced Java Developer needed")
                .companyName("Tech Solutions")
                .location("New York, NY")
                .salaryRange("$80,000 - $120,000")
                .url("https://techsolutions.com/careers/java-developer/123")
                .build();
        offerFacade.saveOffer(offerDto1);
        // when

        // then
    }



}