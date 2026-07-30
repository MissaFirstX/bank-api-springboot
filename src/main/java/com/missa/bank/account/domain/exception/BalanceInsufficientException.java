package com.missa.bank.account.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class BalanceInsufficientException extends BusinessException {
    public BalanceInsufficientException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
