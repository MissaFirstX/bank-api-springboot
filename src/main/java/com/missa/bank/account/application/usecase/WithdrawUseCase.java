package com.missa.bank.account.application.usecase;

import com.missa.bank.account.api.request.WithdrawRequest;
import com.missa.bank.account.api.response.WithdrawResponse;
import com.missa.bank.account.domain.exception.NotFoundAccountException;
import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.common.domain.valueobject.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawUseCase {
    private final AccountRepository accountRepository;

    public WithdrawResponse execute(Long id, WithdrawRequest request) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundAccountException("Account not found"));
        Money money = new Money(request.amount());

        account.withdraw(money);
        Account saved = accountRepository.save(account);
        return new WithdrawResponse(
                saved.getId(),
                saved.getAccountNumber().accountNumber(),
                saved.getClabe().clabe(),
                saved.getBalance().value()
        );
    }
}
