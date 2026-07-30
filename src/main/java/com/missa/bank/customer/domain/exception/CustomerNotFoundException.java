package com.missa.bank.customer.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends BusinessException {
    public CustomerNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
