package com.missa.bank.account.infrastructure.repository;

import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.infrastructure.persistance.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, Long> {
}
