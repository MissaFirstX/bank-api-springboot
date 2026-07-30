package com.missa.bank.security.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
