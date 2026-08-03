package com.missa.bank.transaction.application.usecase;

import com.missa.bank.account.domain.exception.NotFoundAccountException;
import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.common.domain.exception.AccessDeniedException;
import com.missa.bank.common.domain.valueobject.Money;
import com.missa.bank.security.application.service.CurrentUserService;
import com.missa.bank.security.domain.model.User;
import com.missa.bank.transaction.api.request.CreateTransactionRequest;
import com.missa.bank.transaction.api.response.CreateTransactionResponse;
import com.missa.bank.transaction.domain.exception.SameAccountTransferException;
import com.missa.bank.transaction.domain.model.Transaction;
import com.missa.bank.transaction.domain.repository.TransactionRepository;
import com.missa.bank.transaction.domain.valueobject.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateTransactionUseCase {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CurrentUserService currentUserService;

    @Transactional
    public CreateTransactionResponse execute(CreateTransactionRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        Account sourceAccount = accountRepository.findById(request.sourceAccountId()).orElseThrow(
                () -> new NotFoundAccountException("Source account doesn't exists")
        );

        if (currentUser.isCustomer()) {
            Long customerId = currentUser.getCustomerId();
            if (!Objects.equals(sourceAccount.getCustomerId(), customerId)) {
                throw new AccessDeniedException("You cannot access this account");
            }
        }

        if (request.sourceAccountId().equals(request.destinationAccountId())) {
            throw new SameAccountTransferException("Can not transfer same account");
        }

        Account destinationAccount = accountRepository.findById(request.destinationAccountId()).orElseThrow(
                () -> new NotFoundAccountException("Destination account doesn't exists")
        );

        Money amount = new Money(request.amount());

        sourceAccount.isActive();
        destinationAccount.isActive();

        sourceAccount.withdraw(amount);
        destinationAccount.deposit(amount);


        Transaction transfer = new Transaction(
                amount,
                TransactionType.TRANSFER,
                sourceAccount.getId(),
                destinationAccount.getId(),
                request.description()
        );

        transfer.markAsSuccess();

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transaction savedTransaction = transactionRepository.save(transfer);

        return new CreateTransactionResponse(
                savedTransaction.getId(),
                savedTransaction.getAmount().value(),
                savedTransaction.getCreatedAt(),
                savedTransaction.getDescription(),
                savedTransaction.getReference().value(),
                savedTransaction.getTransactionStatus()
        );
    }
}
