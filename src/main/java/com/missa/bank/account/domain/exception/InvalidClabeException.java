package com.missa.bank.account.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidClabeException extends BusinessException {
    public InvalidClabeException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
