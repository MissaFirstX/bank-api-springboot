package com.missa.bank.security.infrastructure.repository;

import com.missa.bank.security.infrastructure.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

}
