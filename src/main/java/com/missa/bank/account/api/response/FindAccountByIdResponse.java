package com.missa.bank.account.api.response;

import com.missa.bank.account.domain.valueobject.AccountStatus;
import com.missa.bank.account.domain.valueobject.AccountType;

import java.math.BigDecimal;

public record FindAccountByIdResponse(
        Long id,
        String clabe,
        String accountNumber,
        AccountType accountType,
        BigDecimal balance,
        AccountStatus status,
        Long customerId
) {
}
