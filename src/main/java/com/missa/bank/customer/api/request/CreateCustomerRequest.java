package com.missa.bank.customer.api.request;

import com.missa.bank.customer.domain.valueobject.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateCustomerRequest(
        @NotBlank
        String firstName,

        String middleName,

        @NotBlank
        String lastName,

        @NotNull
        LocalDate birthDate,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phoneNumber,

        @NotBlank
        String curp,

        @NotBlank
        String rfc,

        @NotNull
        CustomerType customerType
) {
}
