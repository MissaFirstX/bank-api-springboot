package com.missa.bank.account.application.usecase;

import com.missa.bank.account.api.request.WithdrawRequest;
import com.missa.bank.account.api.response.WithdrawResponse;
import com.missa.bank.account.domain.exception.NotFoundAccountException;
import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.common.domain.exception.AccessDeniedException;
import com.missa.bank.common.domain.valueobject.Money;
import com.missa.bank.security.application.service.CurrentUserService;
import com.missa.bank.security.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WithdrawUseCase {
    private final AccountRepository accountRepository;
    private final CurrentUserService currentUserService;

    public WithdrawResponse execute(Long id, WithdrawRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundAccountException("Account not found"));

        if (currentUser.isCustomer()) {
            Long customerId = currentUser.getCustomerId();
            if (!Objects.equals(account.getCustomerId(), customerId)) {
                throw new AccessDeniedException("You cannot access this account");
            }
        }

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
