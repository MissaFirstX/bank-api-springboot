package com.missa.bank.customer.application.usecase;

import com.missa.bank.common.domain.exception.AlreadyExistsPhoneNumberException;
import com.missa.bank.common.domain.valueobject.PhoneNumber;
import com.missa.bank.customer.api.request.UpdateCustomerPhoneNumberRequest;
import com.missa.bank.customer.api.response.UpdateCustomerPhoneNumberResponse;
import com.missa.bank.customer.domain.exception.CustomerNotFoundException;
import com.missa.bank.customer.domain.model.Customer;
import com.missa.bank.customer.domain.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UpdateCustomerPhoneNumberUseCase {
    private final CustomerRepository customerRepository;

    public UpdateCustomerPhoneNumberUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public UpdateCustomerPhoneNumberResponse execute(Long id, UpdateCustomerPhoneNumberRequest updateCustomerPhoneNumberRequest) {
        PhoneNumber newPhoneNumber = new PhoneNumber(updateCustomerPhoneNumberRequest.newPhoneNumber());
        Customer customer = customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer not found with id: " + id)
                );

        if (customer.getPhoneNumber().equals(newPhoneNumber)) {
            throw new AlreadyExistsPhoneNumberException("Customer phone number already exists");
        }
        customer.changePhone(newPhoneNumber);
        Customer updatedCustomer = customerRepository.save(customer);

        return new UpdateCustomerPhoneNumberResponse(
                updatedCustomer.getId(),
                updatedCustomer.getCustomerName().getFullName(),
                updatedCustomer.getPhoneNumber().value());
    }
}
