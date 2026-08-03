package com.missa.bank.account.application.usecase;

import com.missa.bank.account.api.response.FindAccountsResponse;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.common.domain.exception.AccessDeniedException;
import com.missa.bank.security.application.service.CurrentUserService;
import com.missa.bank.security.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FindAccountsUseCase {
    private final AccountRepository accountRepository;
    private final CurrentUserService currentUserService;

    public Page<FindAccountsResponse> execute(Pageable pageable) {

        User currentUser = currentUserService.getCurrentUser();
        if (currentUser.isCustomer()) {
            throw new AccessDeniedException("Cannot access to accounts");
        }

        return accountRepository.search(pageable)
                .map(
                        account -> new FindAccountsResponse(
                                account.getId(),
                                account.getClabe().clabe(),
                                account.getAccountNumber().accountNumber(),
                                account.getAccountType(),
                                account.getBalance().value(),
                                account.getAccountStatus(),
                                account.getCustomerId()
                        )
                );
    }
}
