package com.missa.bank.security.domain.valueobject;

import com.missa.bank.security.domain.exception.InvalidUsernameException;

public record Username(String value) {

    public Username {
        value = value.trim().toLowerCase();
        if (value == null || value.isBlank()) throw new InvalidUsernameException("Username can not be null.");
    }
}
