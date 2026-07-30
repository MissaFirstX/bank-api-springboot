package com.missa.bank.customer.domain.repository;

import com.missa.bank.customer.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    Page<Customer> search(Pageable pageable);

    boolean existsByEmail(String email);
}

