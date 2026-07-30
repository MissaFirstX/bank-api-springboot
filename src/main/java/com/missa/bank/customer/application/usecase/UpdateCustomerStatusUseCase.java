package com.missa.bank.customer.application.usecase;

import com.missa.bank.customer.api.response.UpdateCustomerStatusResponse;
import com.missa.bank.customer.domain.exception.CustomerNotFoundException;
import com.missa.bank.customer.domain.model.Customer;
import com.missa.bank.customer.domain.repository.CustomerRepository;
import com.missa.bank.customer.domain.valueobject.CustomerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerStatusUseCase {
    private final CustomerRepository customerRepository;

    public UpdateCustomerStatusUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public UpdateCustomerStatusResponse execute(Long id, CustomerStatus customerStatus) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer with id " + id + " not found")
        );

        switch (customerStatus) {
            case ACTIVE:
                customer.activate();
                break;
            case INACTIVE:
                customer.deactivate();
                break;
            default:
                System.out.println("Invalid Customer Status");
                break;

        }

        Customer updatedCustomer = customerRepository.save(customer);

        return new UpdateCustomerStatusResponse(updatedCustomer.getId(), updatedCustomer.getCustomerName().getFullName(), updatedCustomer.getStatus());

    }

}
