package com.missa.bank.transaction.application.usecase;

import com.missa.bank.account.domain.exception.NotFoundAccountException;
import com.missa.bank.transaction.api.response.FindTransactionResponse;
import com.missa.bank.transaction.domain.model.Transaction;
import com.missa.bank.transaction.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindTransactionByIdUseCase {
    private final TransactionRepository transactionRepository;

    public FindTransactionResponse execute(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new NotFoundAccountException("Account not found."));
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
