package com.missa.bank.common.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal value) {

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money {
        if (value == null) {
            throw new IllegalArgumentException("Money cannot be null");
        }
        value = value.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Money add(Money money) {
        return new Money(value.add(money.value));
    }

    public Money subtract(Money money) {
        return new Money(value.subtract(money.value));
    }

    public boolean isNegative() {
        return value.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isGreaterThan(Money money) {
        return value.compareTo(money.value) > 0;
    }

    public boolean isLessThan(Money money) {
        return value.compareTo(money.value) < 0;
    }

    public boolean isZero() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }
}
