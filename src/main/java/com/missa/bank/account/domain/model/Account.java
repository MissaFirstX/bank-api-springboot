package com.missa.bank.account.domain.model;

import com.missa.bank.account.domain.exception.*;
import com.missa.bank.account.domain.valueobject.AccountNumber;
import com.missa.bank.account.domain.valueobject.AccountStatus;
import com.missa.bank.account.domain.valueobject.AccountType;
import com.missa.bank.account.domain.valueobject.Clabe;
import com.missa.bank.common.domain.valueobject.Money;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Account {
    private Long id;
    private AccountNumber accountNumber;
    private Clabe clabe;
    private Money balance;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Long customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //UseCase
    public Account(AccountType accountType, Long customerId) {
        this.id = null;
        this.accountNumber = AccountNumber.create();
        this.clabe = Clabe.create();
        this.balance = Money.ZERO;
        this.accountType = accountType;
        this.accountStatus = AccountStatus.ACTIVE;
        this.customerId = customerId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Mapper
    public Account(Long id, AccountNumber accountNumber, Clabe clabe, Money balance,
                   AccountType accountType, AccountStatus accountStatus, Long customerId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.clabe = clabe;
        this.balance = balance;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void deposit(Money money) {
        this.isActive();

        if (money.isNegative()) {
            throw new AmountNegativeException("Cannot deposit a negative amount.");
        }

        this.balance = this.balance.add(money);
        this.updateAtNow();
    }

    public void withdraw(Money money) {
        this.isActive();

        if (money.isNegative()) {
            throw new AmountNegativeException("Cannot withdraw a negative amount.");
        }

        if (this.balance.isLessThan(money)) {
            throw new BalanceInsufficientException("Insufficient balance");
        }

        this.balance = this.balance.subtract(money);
        this.updateAtNow();
    }

    public void block() {
        if (this.accountStatus.equals(AccountStatus.CLOSED)) {
            throw new AccountCannotBeBlockedException("Account cannot be blocked");
        }
        this.accountStatus = AccountStatus.BLOCKED;
        this.updateAtNow();
    }

    public void close() {
        if (!this.balance.isZero()) {
            throw new AccountCannotBeClosedException("Cannot close account, balance is not zero");
        }
        this.accountStatus = AccountStatus.CLOSED;
        this.updateAtNow();
    }

    public void activate() {
        if (this.accountStatus.equals(AccountStatus.CLOSED)) {
            throw new AccountClosedException("Account closed");
        }
        this.accountStatus = AccountStatus.ACTIVE;
        this.updateAtNow();
    }

    public void isActive() {
        if (!this.accountStatus.equals(AccountStatus.ACTIVE)) {
            throw new AccountNotActiveExcpetion("Account is not active." );
        }
    }

    private void updateAtNow() {
        this.updatedAt = LocalDateTime.now();
    }

}
