package com.missa.bank.customer.application.usecase;

import com.missa.bank.customer.api.request.CreateCustomerRequest;
import com.missa.bank.customer.api.response.CreateCustomerResponse;
import com.missa.bank.customer.domain.exception.CustomerAlreadyExistsException;
import com.missa.bank.customer.domain.model.Customer;
import com.missa.bank.customer.domain.repository.CustomerRepository;
import com.missa.bank.customer.domain.valueobject.*;
import com.missa.bank.common.domain.valueobject.Email;
import com.missa.bank.common.domain.valueobject.PhoneNumber;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerUseCase {
    private final CustomerRepository customerRepository;

    @Transactional
    public CreateCustomerResponse execute(CreateCustomerRequest request) {
        if (customerRepository.existsByEmail(request.email())) {
            throw new CustomerAlreadyExistsException("Email already registered");
        }
        CustomerName customerName = new CustomerName(request.firstName(), request.middleName(), request.lastName());
        Email email = new Email(request.email());
        PhoneNumber phoneNumber = new PhoneNumber(request.phoneNumber());
        Curp curp = new Curp(request.curp());
        Rfc rfc = new Rfc(request.rfc());
        BirthDate birthDate = new BirthDate(request.birthDate());
        CustomerType customerType = request.customerType();

        Customer customer = new Customer(customerName, birthDate, email, phoneNumber, curp, rfc, customerType);

        Customer saved = customerRepository.save(customer);
        return new CreateCustomerResponse(saved.getId(), saved.getCustomerName().getFullName(), saved.getStatus());

    }
}
