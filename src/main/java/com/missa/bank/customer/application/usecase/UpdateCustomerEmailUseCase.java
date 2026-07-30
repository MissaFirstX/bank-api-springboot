package com.missa.bank.customer.application.usecase;

import com.missa.bank.common.domain.exception.AlreadyExistsEmailException;
import com.missa.bank.common.domain.valueobject.Email;
import com.missa.bank.customer.api.request.UpdateCustomerEmailRequest;
import com.missa.bank.customer.api.response.UpdateCustomerEmailResponse;
import com.missa.bank.customer.domain.exception.CustomerNotFoundException;
import com.missa.bank.customer.domain.model.Customer;
import com.missa.bank.customer.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerEmailUseCase {
    private final CustomerRepository customerRepository;

    public UpdateCustomerEmailUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public UpdateCustomerEmailResponse execute(Long id, UpdateCustomerEmailRequest updateCustomerEmailRequest) {
        Email newEmail = new Email(updateCustomerEmailRequest.newEmail());
        Customer customer = customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer not found with id: " + id)
                );

        if (newEmail.equals(customer.getEmail())) {
            throw new AlreadyExistsEmailException("Email already exists");
        }

        customer.changeEmail(newEmail);
        Customer updated = customerRepository.save(customer);

        return new UpdateCustomerEmailResponse(updated.getId(), updated.getCustomerName().getFullName(), updated.getEmail().value());

    }
}
