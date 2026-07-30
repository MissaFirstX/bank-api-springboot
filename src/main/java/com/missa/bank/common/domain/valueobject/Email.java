package com.missa.bank.common.domain.valueobject;

import com.missa.bank.common.domain.exception.InvalidEmailException;

import java.util.regex.Pattern;

public record Email(String value) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    public Email {

        if (value == null) throw new InvalidEmailException("Email value cannot be null");

        value = value.trim().toLowerCase();

        if (!EMAIL_PATTERN.matcher(value).matches()) throw new InvalidEmailException("Invalid email address");
    }
}