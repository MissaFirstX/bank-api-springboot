package com.missa.bank.account.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundAccountException extends BusinessException {
    public NotFoundAccountException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
