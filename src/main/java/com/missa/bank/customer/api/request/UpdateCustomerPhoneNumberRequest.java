package com.missa.bank.customer.api.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerPhoneNumberRequest(@NotBlank String newPhoneNumber) {
}
