package com.missa.bank.account.api.request;

import com.missa.bank.account.domain.valueobject.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccountRequest(
        @NotNull
        AccountType accountType,
        @NotNull
        Long customerId
) {
}
