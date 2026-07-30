package com.missa.bank.account.domain.exception;

import com.missa.bank.common.infrastructure.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AccountNotActiveExcpetion extends BusinessException {
    public AccountNotActiveExcpetion(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
