package com.jobfinder.domain.login;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
