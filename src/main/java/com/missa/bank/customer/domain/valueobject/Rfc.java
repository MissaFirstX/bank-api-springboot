package com.missa.bank.customer.domain.valueobject;

import com.missa.bank.customer.domain.exception.InvalidRfcException;

import java.util.regex.Pattern;

public record Rfc(String value) {
    private static final Pattern RFC_PATTERN = Pattern.compile(
            "^([A-ZÑ&]{3,4})(\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01]))([A-Z\\d]{2})([A\\d])$"
    );

    public Rfc {
        if (value == null) throw new InvalidRfcException("RFC value can't be null");

        value = value.trim().toUpperCase();

        if (!RFC_PATTERN.matcher(value).matches()) throw new InvalidRfcException("RFC format is not valid");
    }
}
