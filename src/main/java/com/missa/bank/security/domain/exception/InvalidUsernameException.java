package com.missa.bank.security.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidUsernameException extends BusinessException {
    public InvalidUsernameException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
