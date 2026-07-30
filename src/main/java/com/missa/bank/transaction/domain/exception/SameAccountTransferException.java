package com.missa.bank.transaction.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class SameAccountTransferException extends BusinessException {
    public SameAccountTransferException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
