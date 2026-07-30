package com.missa.bank.transaction.application.usecase;

import com.missa.bank.transaction.api.response.FindTransactionResponse;
import com.missa.bank.transaction.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindTransactionsUseCase {
    private final TransactionRepository transactionRepository;

    public Page<FindTransactionResponse> execute(Pageable pageable) {
        return transactionRepository.search(pageable)
                .map(t -> new FindTransactionResponse(
                                t.getId(),
                                t.getAmount().value(),
                                t.getDescription(),
                                t.getReference().value(),
                                t.getTransactionType(),
                                t.getTransactionStatus(),
                                t.getSourceAccountId(),
                                t.getDestinationAccountId(),
                                t.getCreatedAt()
                        )
                );
    }
}
