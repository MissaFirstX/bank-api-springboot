package com.missa.bank.customer.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidAgeException extends BusinessException {
    public InvalidAgeException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
