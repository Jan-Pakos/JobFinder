package com.jobfinder.domain.offer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryOfferRepository implements OfferRepository {

    ConcurrentHashMap<String, Offer> db = new ConcurrentHashMap<>();

//    @Override
//    public Offer save(Offer offer) {
//        db.put(counter.incrementAndGet(), offer);
//        return offer;
//    }


    @Override
    public Offer save(Offer entity) {
        if (db.values().stream().anyMatch(offer -> offer.url().equals(entity.url()))) {
            throw new OfferDuplicateException(entity.url());
        }
        UUID id = UUID.randomUUID();
        Offer offer = new Offer(
                id.toString(),
                entity.title(),
                entity.companyName(),
                entity.salaryRange(),
                entity.url()
        );
        db.put(id.toString(), offer);
        return offer;
    }

    @Override
    public List<Offer> saveAll(List<Offer> offers) {
        return offers.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public Optional<Offer> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public <S extends Offer> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Offer> findAll() {
        return db.values().stream().toList();
    }

    @Override
    public List<Offer> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Offer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Offer> entities) {

    }

    @Override
    public void deleteAll() {

    }


    @Override
    public boolean existsByUrl(String offerUrl) {
        Set<Offer> collect = db.values().stream().filter(
                offer -> offer.url().equals(offerUrl))
                .collect(Collectors.toSet());
        return !collect.isEmpty();
    }

    @Override
    public <S extends Offer> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Offer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Offer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Offer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Offer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Offer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Offer> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Offer> findAll(Pageable pageable) {
        return null;
    }
}
