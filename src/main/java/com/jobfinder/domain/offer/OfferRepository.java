package com.jobfinder.domain.offer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {


//    Optional<Offer> findOfferById(String id);

    List<Offer> saveAll(List<Offer> offers);

    boolean existsByUrl(String offerUrl);



}
