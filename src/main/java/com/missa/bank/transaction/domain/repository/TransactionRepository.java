package com.missa.bank.transaction.domain.repository;

import com.missa.bank.transaction.domain.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    Optional<Transaction> findById(Long id);

    Page<Transaction> search(Pageable pageable);

    Page<Transaction> findByAccountId(Long accountId, Pageable pageable);
}
