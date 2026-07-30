package com.missa.bank.account.domain.valueobject;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public record AccountNumber(String accountNumber) {

    public AccountNumber {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null");
        }
        if (accountNumber.length() < 11) {
            throw new IllegalArgumentException("Account number cannot be less than 11 characters");
        }
    }

    public static AccountNumber create() {
        long max = 100000000000L;
        long numeroAleatorio = ThreadLocalRandom.current().nextLong(max);

        String numeroFormateado = String.format("%011d", numeroAleatorio);

        return new AccountNumber(numeroFormateado);

    }
}
