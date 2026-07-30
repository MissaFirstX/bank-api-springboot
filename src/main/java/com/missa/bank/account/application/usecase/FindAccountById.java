package com.missa.bank.account.application.usecase;

import com.missa.bank.account.api.response.FindAccountByIdResponse;
import com.missa.bank.account.domain.exception.NotFoundAccountException;
import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAccountById {
    private final AccountRepository accountRepository;

    public FindAccountByIdResponse execute(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundAccountException("Account doesn't exists")
                );
        return new FindAccountByIdResponse(
                account.getId(),
                account.getClabe().clabe(),
                account.getAccountNumber().accountNumber(),
                account.getAccountType(),
                account.getBalance().value(),
                account.getAccountStatus(),
                account.getCustomerId()
        );
    }


}
