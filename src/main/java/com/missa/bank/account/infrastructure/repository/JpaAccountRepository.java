package com.missa.bank.account.infrastructure.repository;

import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.account.infrastructure.persistance.entity.AccountEntity;
import com.missa.bank.account.infrastructure.persistance.mapper.AccountMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaAccountRepository implements AccountRepository {

    private final SpringDataAccountRepository repository;
    private final AccountMapper mapper;

    public JpaAccountRepository(SpringDataAccountRepository repository, AccountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = mapper.toEntity(account);
        AccountEntity savedEntity = repository.save(accountEntity);
        return mapper.toAccount(savedEntity);
    }

    @Override
    public Optional<Account> findById(long id) {
        return repository.findById(id).map(mapper::toAccount);
    }

    @Override
    public Page<Account> search(Pageable pageable) {
        Page<AccountEntity> accountEntities = repository.findAll(pageable);
        return accountEntities.map(mapper::toAccount);
    }
}
