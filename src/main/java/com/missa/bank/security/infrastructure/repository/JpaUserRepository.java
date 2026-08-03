package com.missa.bank.security.infrastructure.repository;

import com.missa.bank.security.domain.model.User;
import com.missa.bank.security.domain.repository.UserRepository;
import com.missa.bank.security.infrastructure.persistance.entity.UserEntity;
import com.missa.bank.security.infrastructure.persistance.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {
    private final SpringDataUserRepository repository;
    private final UserMapper mapper;

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return repository.findByUsername(username)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByCustomerId(Long customerId) {
        return repository.existsByCustomerId(customerId);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);

    }
}
