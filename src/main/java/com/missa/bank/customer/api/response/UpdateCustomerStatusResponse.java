package com.missa.bank.customer.api.response;

import com.missa.bank.customer.domain.valueobject.CustomerStatus;

public record UpdateCustomerStatusResponse(Long id, String name, CustomerStatus status) {
}
