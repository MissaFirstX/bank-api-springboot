package com.missa.bank.transaction.application.usecase;

import com.missa.bank.account.domain.exception.NotFoundAccountException;
import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.common.domain.exception.AccessDeniedException;
import com.missa.bank.security.application.service.CurrentUserService;
import com.missa.bank.security.domain.model.User;
import com.missa.bank.transaction.api.response.FindTransactionResponse;
import com.missa.bank.transaction.domain.exception.NotFoundTransactionException;
import com.missa.bank.transaction.domain.model.Transaction;
import com.missa.bank.transaction.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FindTransactionByIdUseCase {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CurrentUserService currentUserService;

    public FindTransactionResponse execute(Long id) {
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

        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new NotFoundTransactionException("Transactions in account not found."));
        return new FindTransactionResponse(
                transaction.getId(),
                transaction.getAmount().value(),
                transaction.getDescription(),
                transaction.getReference().value(),
                transaction.getTransactionType(),
                transaction.getTransactionStatus(),
                transaction.getSourceAccountId(),
                transaction.getDestinationAccountId(),
                transaction.getCreatedAt()
        );
    }

}
