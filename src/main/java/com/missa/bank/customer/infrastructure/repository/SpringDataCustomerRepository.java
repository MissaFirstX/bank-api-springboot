package com.missa.bank.customer.infrastructure.repository;

import com.missa.bank.customer.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataCustomerRepository
        extends JpaRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {
    boolean existsByEmail(String email);

}