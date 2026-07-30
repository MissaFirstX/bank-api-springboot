package com.missa.bank.account.api.response;

import com.missa.bank.transaction.domain.valueobject.TransactionStatus;
import com.missa.bank.transaction.domain.valueobject.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FindTransactionsByAccountResponse(
        String reference,
        TransactionType type,
        BigDecimal amount,
        TransactionStatus status,
        String description,
        LocalDateTime createdAt,
        Long sourceAccountId,
        Long destinationAccountId

) {
}
