package com.jobfinder.domain.offer;

import com.jobfinder.domain.offer.dto.OfferDto;
import com.jobfinder.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class OfferUpdater {

    private final OfferRepository offerRepository;

    public OfferResponseDto partiallyUpdateOffer(OfferDto offerDto, String id) {
        Offer offerById = offerRepository.findOfferById(id).orElseThrow(() -> new OfferNotFoundException(id));
        if (!offerById.title().equals(offerDto.title())) {
            String newTitle = offerDto.title();
        }
        if (!offerById.companyName().equals(offerDto.company())) {
            String newCompanyName = offerDto.company();
        }
        if (!offerById.salaryRange().equals(offerDto.salary())) {
            String newSalary = offerDto.salary();
        }
        if (!offerById.url().equals(offerDto.offerUrl())) {
            String newUrl = offerDto.offerUrl();
        }
        Offer offerToSave = Offer.builder().build();
    }
}
