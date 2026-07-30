package com.missa.bank.account.infrastructure.persistance.entity;

import com.missa.bank.account.domain.valueobject.AccountStatus;
import com.missa.bank.account.domain.valueobject.AccountType;
import com.missa.bank.customer.domain.model.Customer;
import com.missa.bank.customer.infrastructure.persistence.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String clabe;
    private BigDecimal balance;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
