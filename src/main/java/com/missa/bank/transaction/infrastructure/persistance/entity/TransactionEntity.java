package com.missa.bank.transaction.infrastructure.persistance.entity;

import com.missa.bank.common.domain.valueobject.Money;
import com.missa.bank.transaction.domain.valueobject.TransactionReference;
import com.missa.bank.transaction.domain.valueobject.TransactionStatus;
import com.missa.bank.transaction.domain.valueobject.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private Long sourceAccountId;
    private Long destinationAccountId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
