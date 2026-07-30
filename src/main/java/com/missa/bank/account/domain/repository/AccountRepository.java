package com.missa.bank.account.domain.repository;

import com.missa.bank.account.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);

    Optional<Account> findById(long id);

    Page<Account> search(Pageable pageable);
}
