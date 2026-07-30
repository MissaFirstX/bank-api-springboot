package com.missa.bank.security.domain.valueobject;

import com.missa.bank.security.domain.exception.InvalidPasswordException;

public record Password(String value) {
    public Password {
        value = value.trim();
        if (value == null || value.isBlank()) throw new InvalidPasswordException("Password can not be null.");
        if (value.length() < 8) throw new InvalidPasswordException("Password length may be at least 8.");
    }
}
