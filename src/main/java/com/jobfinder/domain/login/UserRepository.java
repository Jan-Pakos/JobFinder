package com.jobfinder.domain.login;

import java.util.Optional;

public interface UserRepository {

    boolean existsByUsername(String username);

    User save(User user);

    Optional<User> findByUsername(String username);
}
