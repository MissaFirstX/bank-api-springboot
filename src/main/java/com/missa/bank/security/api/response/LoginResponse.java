package com.missa.bank.security.api.response;

public record LoginResponse(
        String accessToken,
        String tokenType,
        Long userId,
        String username,
        String role
) {
}
