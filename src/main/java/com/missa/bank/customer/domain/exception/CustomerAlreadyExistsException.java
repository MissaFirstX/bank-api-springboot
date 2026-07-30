package com.missa.bank.customer.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CustomerAlreadyExistsException extends BusinessException {
    public CustomerAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
