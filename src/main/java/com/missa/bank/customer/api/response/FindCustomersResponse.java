package com.missa.bank.customer.api.response;


import com.missa.bank.customer.domain.valueobject.CustomerStatus;

public record FindCustomersResponse(Long customerId, String firstName, String lastName, String email,
                                    CustomerStatus customerStatus) {

}
