package com.missa.bank.customer.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidRfcException extends BusinessException {
    public InvalidRfcException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
