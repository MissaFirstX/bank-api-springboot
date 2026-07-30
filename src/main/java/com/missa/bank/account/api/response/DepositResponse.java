package com.missa.bank.account.api.response;

import java.math.BigDecimal;

public record DepositResponse(
        Long id,
        String accountNumber,
        String clabe,
        BigDecimal balance
) {
}
