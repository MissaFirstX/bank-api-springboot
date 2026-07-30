package com.missa.bank.common.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidEmailException extends BusinessException {
    public InvalidEmailException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
