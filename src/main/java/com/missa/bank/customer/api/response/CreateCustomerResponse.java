package com.missa.bank.customer.api.response;

import com.missa.bank.customer.domain.valueobject.CustomerName;
import com.missa.bank.customer.domain.valueobject.CustomerStatus;

public record CreateCustomerResponse(Long id, String name, CustomerStatus status) {
}
