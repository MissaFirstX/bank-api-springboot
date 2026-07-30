package com.missa.bank.common.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AlreadyExistsPhoneNumberException extends BusinessException {
    public AlreadyExistsPhoneNumberException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
