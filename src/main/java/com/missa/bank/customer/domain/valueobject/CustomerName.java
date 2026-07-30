package com.missa.bank.customer.domain.valueobject;

public record CustomerName(String firstName, String middleName, String lastName) {

    public String getFullName() {

        if (middleName.isEmpty()) {
            return firstName + " " + lastName;
        }

        return firstName + " " + middleName + " " + lastName;
    }
}
