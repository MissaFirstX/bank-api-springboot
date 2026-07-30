package com.missa.bank.security.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
