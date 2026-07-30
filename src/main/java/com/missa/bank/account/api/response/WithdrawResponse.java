package com.missa.bank.account.api.response;

import java.math.BigDecimal;

public record WithdrawResponse(
        Long id,
        String accountNumber,
        String clabe,
        BigDecimal balance
) {
}
