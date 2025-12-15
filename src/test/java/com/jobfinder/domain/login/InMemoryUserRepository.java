package com.jobfinder.domain.login;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryUserRepository implements UserRepository {

    ConcurrentHashMap<Long, User> db = new ConcurrentHashMap<>();
    AtomicLong counter = new AtomicLong(0);

    @Override
    public boolean existsByUsername(String username) {
        return db.values().stream().anyMatch(user -> user.username().equals(username));
    }

    @Override
    public User save(User user) {
        long index = counter.incrementAndGet();
        db.put(index, user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return db.values().stream()
                .filter(user -> user.username().equals(username))
                .findFirst();
    }
}
