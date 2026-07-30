package com.missa.bank.account.application.usecase;

import com.missa.bank.account.api.response.FindTransactionsByAccountResponse;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.transaction.domain.model.Transaction;
import com.missa.bank.transaction.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindTransactionsByAccountUseCase {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Page<FindTransactionsByAccountResponse> execute(Long accountId, Pageable pageable) {
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
