package com.missa.bank.transaction.infrastructure.repository;

import com.missa.bank.transaction.infrastructure.persistance.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity, Long> {
    Page<TransactionEntity> findBySourceAccountId(
            Long sourceAccountId,
            Pageable pageable
    );
}
