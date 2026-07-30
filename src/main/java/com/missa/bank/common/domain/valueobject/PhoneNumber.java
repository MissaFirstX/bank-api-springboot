package com.missa.bank.common.domain.valueobject;

import com.missa.bank.common.domain.exception.InvalidPhoneNumberException;

import java.util.regex.Pattern;

public record PhoneNumber(String value) {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$");

    public PhoneNumber{
        if (value == null) throw new InvalidPhoneNumberException("Phone number cannot be null");
        if (!PHONE_PATTERN.matcher(value).matches()) throw new InvalidPhoneNumberException("Invalid phone number");
    }
}
