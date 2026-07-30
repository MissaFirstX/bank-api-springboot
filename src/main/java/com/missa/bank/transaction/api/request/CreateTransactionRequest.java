package com.missa.bank.transaction.api.request;

import com.missa.bank.transaction.domain.valueobject.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateTransactionRequest(
        @NotNull
        Long sourceAccountId,

        @NotNull
        Long destinationAccountId,

        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal amount,

        String description
) {
}
