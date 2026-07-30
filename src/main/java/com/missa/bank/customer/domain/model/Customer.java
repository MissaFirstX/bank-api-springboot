package com.missa.bank.customer.domain.model;

import com.missa.bank.customer.domain.valueobject.*;
import com.missa.bank.common.domain.exception.InvalidEmailException;
import com.missa.bank.common.domain.exception.InvalidPhoneNumberException;
import com.missa.bank.common.domain.valueobject.Email;
import com.missa.bank.common.domain.valueobject.PhoneNumber;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Customer {
    private final Long id;
    private CustomerName customerName;
    private BirthDate birthDate;
    private Email email;
    private PhoneNumber phoneNumber;
    private Curp curp;
    private Rfc rfc;
    private CustomerType customerType;
    private CustomerStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // UseCases
    public Customer(CustomerName customerName, BirthDate birthDate, Email email, PhoneNumber phoneNumber, Curp curp, Rfc rfc, CustomerType customerType) {
        this.id = null;
        this.customerName = customerName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.curp = curp;
        this.rfc = rfc;
        this.customerType = customerType;
        this.status = CustomerStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Mapper
    public Customer(Long id, CustomerName customerName, BirthDate birthDate, Email email, PhoneNumber phoneNumber, Curp curp, Rfc rfc, CustomerType customerType, CustomerStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.customerName = customerName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.curp = curp;
        this.rfc = rfc;
        this.customerType = customerType;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void changeEmail(Email email) {
        if (this.email.equals(email)) throw new InvalidEmailException("Email cannot be the same as the email");

        this.email = email;
        this.updatedAt = LocalDateTime.now();
    }

    public void changePhone(PhoneNumber phoneNumber) {
        if (this.phoneNumber.equals(phoneNumber))
            throw new InvalidPhoneNumberException("Phone number cannot be the same as the phone number");

        this.phoneNumber = phoneNumber;
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.status = CustomerStatus.INACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        this.status = CustomerStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }
}
