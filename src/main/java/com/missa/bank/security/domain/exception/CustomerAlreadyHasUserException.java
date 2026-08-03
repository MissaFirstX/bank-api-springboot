package com.missa.bank.security.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CustomerAlreadyHasUserException extends BusinessException {
    public CustomerAlreadyHasUserException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
