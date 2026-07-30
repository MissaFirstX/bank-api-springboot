package com.missa.bank.security.domain.repository;

import com.missa.bank.security.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByUserName(String username);

    boolean existsByUsername(String username);

    Optional<User> findById(Long id);
}
