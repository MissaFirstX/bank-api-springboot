package com.missa.bank.transaction.api.response;

import com.missa.bank.transaction.domain.valueobject.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateTransactionResponse(
        Long id,
        BigDecimal amount,
        LocalDateTime createdAt,
        String description,
        String reference,
        TransactionStatus status
) {
}
