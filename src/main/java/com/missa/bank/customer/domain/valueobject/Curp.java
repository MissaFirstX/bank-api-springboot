package com.missa.bank.customer.domain.valueobject;

import com.missa.bank.customer.domain.exception.InvalidCurpException;

import java.util.regex.Pattern;

public record Curp(String value) {

    private static final Pattern CURP_PATTERN = Pattern.compile(
            "^[A-Z][AEIOU][A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[HM]" +
                    "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
                    "[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z][0-9]$"
    );

    public Curp {
        if (value == null) throw new InvalidCurpException("CURP value cannot be null");

        value = value.trim().toUpperCase();

        if (!CURP_PATTERN.matcher(value).matches()) {
            throw new InvalidCurpException("El formato de la CURP es inválido: " + value);
        }

    }
}
