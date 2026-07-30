package com.missa.bank.security.infrastructure.persistance.mapper;

import com.missa.bank.security.domain.model.User;
import com.missa.bank.security.domain.valueobject.Password;
import com.missa.bank.security.domain.valueobject.Username;
import com.missa.bank.security.infrastructure.persistance.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername().value(),
                user.getPassword().value(),
                user.getRole(),
                user.isEnabled(),
                user.getCustomerId(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public User toDomain(UserEntity entity) {
        Username username = new Username(entity.getUsername());
        Password password = new Password(entity.getPassword());
        return new User(
                entity.getId(),
                username,
                password,
                entity.getRole(),
                entity.isEnabled(),
                entity.getCustomerId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }


}
