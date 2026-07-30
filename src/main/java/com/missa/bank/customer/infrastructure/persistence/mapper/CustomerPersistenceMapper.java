package com.missa.bank.customer.infrastructure.persistence.mapper;

import com.missa.bank.customer.domain.model.Customer;
import com.missa.bank.customer.domain.valueobject.*;
import com.missa.bank.customer.infrastructure.persistence.entity.CustomerEntity;
import com.missa.bank.common.domain.valueobject.Email;
import com.missa.bank.common.domain.valueobject.PhoneNumber;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomerPersistenceMapper {

    public CustomerEntity toEntity(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(customer.getId());
        customerEntity.setFirstName(customer.getCustomerName().firstName());
        customerEntity.setMiddleName(customer.getCustomerName().middleName());
        customerEntity.setLastName(customer.getCustomerName().lastName());
        customerEntity.setEmail(customer.getEmail().value());
        customerEntity.setPhoneNumber(customer.getPhoneNumber().value());
        customerEntity.setBirthDate(customer.getBirthDate().value());
        customerEntity.setCurp(customer.getCurp().value());
        customerEntity.setRfc(customer.getRfc().value());
        customerEntity.setCustomerType(customer.getCustomerType());
        customerEntity.setStatus(customer.getStatus());
        customerEntity.setCreatedAt(customer.getCreatedAt());
        customerEntity.setUpdatedAt(customer.getUpdatedAt());

        return customerEntity;
    }

    public Customer toDomain(CustomerEntity entity) {
        Long id = entity.getId();
        CustomerName customerName = new CustomerName(entity.getFirstName(), entity.getMiddleName(), entity.getLastName());
        Email email = new Email(entity.getEmail());
        PhoneNumber phoneNumber = new PhoneNumber(entity.getPhoneNumber());
        BirthDate birthDate = new BirthDate(entity.getBirthDate());
        Curp curp = new Curp(entity.getCurp());
        Rfc rfc = new Rfc(entity.getRfc());
        CustomerType customerType = entity.getCustomerType();
        LocalDateTime createdAt = entity.getCreatedAt();
        LocalDateTime updatedAt = entity.getUpdatedAt();
        CustomerStatus customerStatus = entity.getStatus();

        return new Customer(id, customerName, birthDate, email, phoneNumber, curp, rfc, customerType, customerStatus, createdAt, updatedAt);

    }
}
