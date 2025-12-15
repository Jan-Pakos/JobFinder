package com.jobfinder.domain.offer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OfferRepository {

    Offer save(Offer offer);

    Collection<Offer> findAll();

    Optional<Offer> findById(Long id);

    boolean existsByUrl(String offerUrl);

    List<Offer> saveAll(List<Offer> newOffers);

}
