package com.missa.bank.transaction.domain.model;

import com.missa.bank.common.domain.valueobject.Money;
import com.missa.bank.transaction.domain.valueobject.TransactionReference;
import com.missa.bank.transaction.domain.valueobject.TransactionStatus;
import com.missa.bank.transaction.domain.valueobject.TransactionType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Transaction {
    private Long id;
    private TransactionReference reference;
    private Money amount;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // UseCase
    public Transaction(Money amount, TransactionType transactionType, Long sourceAccountId, Long destinationAccountId, String description) {
        this.id = null;
        this.reference = TransactionReference.create();
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionStatus = TransactionStatus.PENDING;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Domain
    public Transaction(Long id, TransactionReference reference, Money amount, TransactionType transactionType, TransactionStatus transactionStatus, Long sourceAccountId, Long destinationAccountId, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.reference = reference;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void markAsSuccess() {
        this.transactionStatus = TransactionStatus.SUCCESS;
        this.updatedAt = LocalDateTime.now();
    }


    public void markAsFailed() {
        this.transactionStatus = TransactionStatus.FAILED;
        this.updatedAt = LocalDateTime.now();
    }


    public void markAsReversed() {
        this.transactionStatus = TransactionStatus.REVERSED;
        this.updatedAt = LocalDateTime.now();
    }


}
