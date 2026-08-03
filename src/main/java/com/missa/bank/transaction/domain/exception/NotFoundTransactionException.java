package com.missa.bank.transaction.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundTransactionException extends BusinessException {
    public NotFoundTransactionException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
