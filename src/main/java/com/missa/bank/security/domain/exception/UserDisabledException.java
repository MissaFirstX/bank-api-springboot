package com.missa.bank.security.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserDisabledException extends BusinessException {
    public UserDisabledException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
