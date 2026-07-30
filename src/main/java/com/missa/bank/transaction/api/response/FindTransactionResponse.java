package com.missa.bank.transaction.api.response;

import com.missa.bank.transaction.domain.valueobject.TransactionStatus;
import com.missa.bank.transaction.domain.valueobject.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FindTransactionResponse(
        Long id,
        BigDecimal amount,
        String description,
        String reference,
        TransactionType type,
        TransactionStatus status,
        Long sourceAccountId,
        Long destinationAccountId,
        LocalDateTime createdAt
) {
}
