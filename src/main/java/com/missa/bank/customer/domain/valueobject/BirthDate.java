package com.missa.bank.customer.domain.valueobject;

import com.missa.bank.customer.domain.exception.InvalidAgeException;

import java.time.LocalDate;
import java.time.Period;

public record BirthDate(LocalDate value) {
    public BirthDate {
        Period age = Period.between(value, LocalDate.now());
        if (age.getYears() < 18) {
            throw new InvalidAgeException("Customer has to be 18 years old");
        }
    }
}
