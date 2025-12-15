package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferResponseDto;

import java.util.List;

public class OfferFacadeTestConfiguration {

    private final InMemoryFetcherTestImpl fetcher;
    private final InMemoryOfferRepository repository;

    public OfferFacadeTestConfiguration() {
        this.fetcher = new InMemoryFetcherTestImpl(
                List.of(
                        OfferResponseDto.builder()
                                .id(1L)
                                .title("Offer 1")
                                .companyName("Company 1")
                                .description("Description 1")
                                .salaryRange("1000")
                                .location("Location 1")
                                .url("url/1")
                                .build(),
                        OfferResponseDto.builder()
                                .id(2L)
                                .title("Offer 2")
                                .companyName("Company 2")
                                .description("Description 2")
                                .salaryRange("2000")
                                .location("Location 2")
                                .url("url/2")
                                .build(),
                        OfferResponseDto.builder()
                                .id(3L)
                                .title("Offer 3")
                                .companyName("Company 3")
                                .description("Description 3")
                                .salaryRange("3000")
                                .location("Location 3")
                                .url("url/3")
                                .build(),
                        OfferResponseDto.builder()
                                .id(4L)
                                .title("Offer 4")
                                .companyName("Company 4")
                                .description("Description 4")
                                .salaryRange("4000")
                                .location("Location 4")
                                .url("url/4")
                                .build(),
                        OfferResponseDto.builder()
                                .id(5L)
                                .title("Offer 5")
                                .companyName("Company 5")
                                .description("Description 5")
                                .salaryRange("5000")
                                .location("Location 5")
                                .url("url/5")
                                .build(),
                        OfferResponseDto.builder()
                                .id(6L)
                                .title("Offer 6")
                                .companyName("Company 6")
                                .description("Description 6")
                                .salaryRange("6000")
                                .location("Location 6")
                                .url("url/6")
                                .build()

                )
        );
        this.repository = new InMemoryOfferRepository();
    }

    public OfferFacadeTestConfiguration(List<OfferResponseDto> remoteServerOffers) {
        this.fetcher = new InMemoryFetcherTestImpl(remoteServerOffers);
        this.repository = new InMemoryOfferRepository();
    }

    OfferFacade offerFacadeForTests() {
        return new OfferFacade(repository, new OfferService( repository, fetcher));
    }

}
