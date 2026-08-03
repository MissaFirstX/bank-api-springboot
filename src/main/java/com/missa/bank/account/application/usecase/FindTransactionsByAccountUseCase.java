package com.missa.bank.account.application.usecase;

import com.missa.bank.account.api.response.FindTransactionsByAccountResponse;
import com.missa.bank.account.domain.exception.NotFoundAccountException;
import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.common.domain.exception.AccessDeniedException;
import com.missa.bank.security.application.service.CurrentUserService;
import com.missa.bank.security.domain.model.User;
import com.missa.bank.transaction.domain.model.Transaction;
import com.missa.bank.transaction.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FindTransactionsByAccountUseCase {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CurrentUserService currentUserService;

    public Page<FindTransactionsByAccountResponse> execute(Long accountId, Pageable pageable) {

        User currentUser = currentUserService.getCurrentUser();

        Account account = accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new NotFoundAccountException("Account doesn't exists")
                );

        if (currentUser.isCustomer()) {
            Long customerId = currentUser.getCustomerId();
            if (!Objects.equals(account.getCustomerId(), customerId)) {
                throw new AccessDeniedException("You cannot access this account");
            }
        }

        return transactionRepository.findByAccountId(accountId, pageable)
                .map(
                        t -> new FindTransactionsByAccountResponse(
                                t.getReference().value(),
                                t.getTransactionType(),
                                t.getAmount().value(),
                                t.getTransactionStatus(),
                                t.getDescription(),
                                t.getCreatedAt(),
                                t.getSourceAccountId(),
                                t.getDestinationAccountId()
                        )
                );
    }


}
