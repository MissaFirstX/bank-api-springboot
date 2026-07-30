package com.missa.bank.common.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AlreadyExistsEmailException extends BusinessException {
    public AlreadyExistsEmailException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
