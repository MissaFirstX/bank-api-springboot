package com.missa.bank.account.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AccountCannotBeBlockedException extends BusinessException {
    public AccountCannotBeBlockedException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
