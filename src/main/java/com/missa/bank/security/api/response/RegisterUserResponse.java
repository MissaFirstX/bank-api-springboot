package com.missa.bank.security.api.response;

public record RegisterUserResponse(
        Long id,
        String username,
        String role
) {
}
