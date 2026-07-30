package com.missa.bank.transaction.domain.valueobject;

import java.util.UUID;

public record TransactionReference(String value) {

    public TransactionReference {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Transaction reference cannot be empty");
        }
    }

    public static TransactionReference create() {
        return new TransactionReference(
                String.valueOf(
                        UUID.randomUUID()
                )
        );
    }
}
