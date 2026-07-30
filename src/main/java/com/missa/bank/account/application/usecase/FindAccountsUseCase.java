package com.missa.bank.account.application.usecase;

import com.missa.bank.account.api.response.FindAccountsResponse;
import com.missa.bank.account.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class FindAccountsUseCase {
    private final AccountRepository accountRepository;

    public Page<FindAccountsResponse> execute(Pageable pageable) {
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
