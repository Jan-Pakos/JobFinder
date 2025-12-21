package com.jobfinder.domain.offer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryOfferRepository implements OfferRepository {

    ConcurrentHashMap<Long, Offer> db = new ConcurrentHashMap<>();
    AtomicLong counter = new AtomicLong(0);

    @Override
    public Offer save(Offer offer) {
        db.put(counter.incrementAndGet(), offer);
        return offer;
    }

    @Override
    public Collection<Offer> findAll() {
        return db.values().stream().toList();
    }

    @Override
    public Optional<Offer> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public boolean existsByUrl(String offerUrl) {
        return false;
    }

    @Override
    public List<Offer> saveAll(List<Offer> offers) {
        return offers.stream().map(this::save).toList();
    }
}
