package com.missa.bank.transaction.infrastructure.repository;

import com.missa.bank.customer.infrastructure.persistence.entity.CustomerEntity;
import com.missa.bank.transaction.domain.model.Transaction;
import com.missa.bank.transaction.domain.repository.TransactionRepository;
import com.missa.bank.transaction.infrastructure.persistance.entity.TransactionEntity;
import com.missa.bank.transaction.infrastructure.persistance.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaTransactionRepository implements TransactionRepository {

    private final SpringDataTransactionRepository springRepository;
    private final TransactionMapper mapper;

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = mapper.toEntity(transaction);
        TransactionEntity saved = springRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return springRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Transaction> search(Pageable pageable) {
        Page<TransactionEntity> entities = springRepository.findAll(pageable);
        return entities.map(mapper::toDomain);
    }

    @Override
    public Page<Transaction> findByAccountId(Long accountId, Pageable pageable) {
        Page<TransactionEntity> entities = springRepository.findBySourceAccountId(accountId, pageable);
        return entities.map(mapper::toDomain);
    }
}
