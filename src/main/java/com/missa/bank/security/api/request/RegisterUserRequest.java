package com.missa.bank.security.api.request;

import com.missa.bank.security.domain.valueobject.Role;

public record RegisterUserRequest(
        String username,
        String password,
        Role role,
        Long customerId
) {
}
