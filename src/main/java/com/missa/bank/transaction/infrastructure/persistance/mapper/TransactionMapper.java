package com.missa.bank.transaction.infrastructure.persistance.mapper;

import com.missa.bank.common.domain.valueobject.Money;
import com.missa.bank.transaction.domain.model.Transaction;
import com.missa.bank.transaction.domain.valueobject.TransactionReference;
import com.missa.bank.transaction.domain.valueobject.TransactionStatus;
import com.missa.bank.transaction.domain.valueobject.TransactionType;
import com.missa.bank.transaction.infrastructure.persistance.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public Transaction toDomain(TransactionEntity entity) {
        TransactionReference reference = new TransactionReference(entity.getReference());
        Money amount = new Money(entity.getAmount());
        TransactionType transactionType = entity.getTransactionType();
        TransactionStatus status = entity.getTransactionStatus();

        return new Transaction(
                entity.getId(),
                reference,
                amount,
                transactionType,
                status,
                entity.getSourceAccountId(),
                entity.getDestinationAccountId(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public TransactionEntity toEntity(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId(),
                transaction.getReference().value(),
                transaction.getAmount().value(),
                transaction.getTransactionType(),
                transaction.getTransactionStatus(),
                transaction.getSourceAccountId(),
                transaction.getDestinationAccountId(),
                transaction.getDescription(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
        );
    }
}
