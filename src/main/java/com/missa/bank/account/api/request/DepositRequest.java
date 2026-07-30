package com.missa.bank.account.api.request;

import java.math.BigDecimal;

public record DepositRequest(
        BigDecimal amount
) {
}
