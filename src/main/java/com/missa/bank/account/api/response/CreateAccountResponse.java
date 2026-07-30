package com.missa.bank.account.api.response;

import com.missa.bank.account.domain.valueobject.AccountStatus;
import com.missa.bank.account.domain.valueobject.AccountType;

import java.time.LocalDateTime;

public record CreateAccountResponse(
        Long id,
        String accountNumber,
        String clabe,
        Long customerId,
        AccountStatus status,
        AccountType accountType,
        LocalDateTime createdAt
) {
}
