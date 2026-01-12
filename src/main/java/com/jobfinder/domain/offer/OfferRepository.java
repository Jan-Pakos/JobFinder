package com.jobfinder.domain.offer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {

    boolean existsByUrl(String offerUrl);
    Optional<Offer> findOfferById(String id);

}
