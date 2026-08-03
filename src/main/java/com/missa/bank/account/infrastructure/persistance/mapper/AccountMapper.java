package com.missa.bank.account.infrastructure.persistance.mapper;

import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.domain.valueobject.AccountNumber;
import com.missa.bank.account.domain.valueobject.AccountStatus;
import com.missa.bank.account.domain.valueobject.AccountType;
import com.missa.bank.account.domain.valueobject.Clabe;
import com.missa.bank.account.infrastructure.persistance.entity.AccountEntity;
import com.missa.bank.common.domain.valueobject.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AccountMapper {
    public AccountEntity toEntity(Account account) {

        return new AccountEntity(
                account.getId(),
                account.getAccountNumber().accountNumber(),
                account.getClabe().clabe(),
                account.getBalance().value(),
                account.getCustomerId(),
                account.getAccountType(),
                account.getAccountStatus(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );

    }

    public Account toAccount(AccountEntity accountEntity) {
        Long id = accountEntity.getId();
        AccountNumber accountNumber = new AccountNumber(accountEntity.getAccountNumber());
        Clabe clabe = new Clabe(accountEntity.getClabe());
        Money balance = new Money(accountEntity.getBalance());
        AccountType accountType = accountEntity.getAccountType();
        AccountStatus accountStatus = accountEntity.getStatus();
        Long customerId = accountEntity.getCustomerId();
        LocalDateTime createdAt = accountEntity.getCreatedAt();
        LocalDateTime updatedAt = accountEntity.getUpdatedAt();

        return new Account(id, accountNumber, clabe, balance, accountType, accountStatus, customerId, createdAt, updatedAt);
    }
}
