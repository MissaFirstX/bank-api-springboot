package com.missa.bank.customer.application.usecase;

import com.missa.bank.customer.api.response.FindCustomerByIdResponse;
import com.missa.bank.customer.domain.exception.CustomerNotFoundException;
import com.missa.bank.customer.domain.model.Customer;
import com.missa.bank.customer.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;


@Service
public class FindCustomerByIdUseCase {
    private final CustomerRepository customerRepository;

    public FindCustomerByIdUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public FindCustomerByIdResponse execute(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer not found with id: " + id)
                );


        return new FindCustomerByIdResponse(
                customer.getId(),
                customer.getCustomerName().getFullName(),
                customer.getEmail().value(),
                customer.getPhoneNumber().value(),
                customer.getBirthDate().value(),
                customer.getCustomerType(),
                customer.getStatus()
        );

    }
}
