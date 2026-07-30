package com.missa.bank.security.domain.model;

import com.missa.bank.security.domain.exception.InvalidPasswordException;
import com.missa.bank.security.domain.valueobject.Password;
import com.missa.bank.security.domain.valueobject.Role;
import com.missa.bank.security.domain.valueobject.Username;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private Long id;
    private Username username;
    private Password password;
    private Role role;
    private boolean enabled;
    private Long customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // UseCase
    public User(Username username, Password password, Role role, Long customerId) {
        this.id = null;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = true;
        this.customerId = customerId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Domain
    public User(Long id, Username username, Password password, Role role, boolean enabled, Long customerId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void changePassword(Password newPassword) {
        if (this.password.equals(newPassword)) throw new InvalidPasswordException("New Password should be different.");
        this.password = newPassword;
        this.updatedAt = LocalDateTime.now();
    }

    public void enable() {
        this.enabled = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void disable() {
        this.enabled = false;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isCustomer() {
        return customerId != null;
    }
}
