package com.missa.bank.account.application.usecase;

import com.missa.bank.account.api.request.DepositRequest;
import com.missa.bank.account.api.response.DepositResponse;
import com.missa.bank.account.domain.exception.NotFoundAccountException;
import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.common.domain.valueobject.Money;
import com.missa.bank.transaction.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DepositUseCase {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public DepositResponse execute(Long id, DepositRequest request) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundAccountException("Account not found"));
        Money money = new Money(request.amount());

        account.deposit(money);
        Account saved = accountRepository.save(account);


        return new DepositResponse(
                saved.getId(),
                saved.getAccountNumber().accountNumber(),
                saved.getClabe().clabe(),
                saved.getBalance().value()
        );
    }
}
