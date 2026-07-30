package com.missa.bank.customer.api.response;

import com.missa.bank.customer.domain.valueobject.CustomerStatus;
import com.missa.bank.customer.domain.valueobject.CustomerType;

import java.time.LocalDate;

public record FindCustomerByIdResponse(Long id, String name, String email, String phone, LocalDate birthdate,
                                       CustomerType customerType, CustomerStatus status) {
}
