package com.missa.bank.account.application.usecase;

import com.missa.bank.account.api.response.FindAccountByIdResponse;
import com.missa.bank.account.domain.exception.NotFoundAccountException;
import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.common.domain.exception.AccessDeniedException;
import com.missa.bank.security.application.service.CurrentUserService;
import com.missa.bank.security.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FindAccountById {
    private final AccountRepository accountRepository;
    private final CurrentUserService currentUserService;

    public FindAccountByIdResponse execute(Long id) {
        User currentUser = currentUserService.getCurrentUser();

        Account account = accountRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundAccountException("Account doesn't exists")
                );

        if (currentUser.isCustomer()) {
            Long customerId = currentUser.getCustomerId();
            if (!Objects.equals(account.getCustomerId(), customerId)) {
                throw new AccessDeniedException("You cannot access this account");
            }
        }

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
