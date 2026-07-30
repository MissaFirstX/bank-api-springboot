package com.missa.bank.customer.application.usecase;

import com.missa.bank.customer.api.response.FindCustomersResponse;
import com.missa.bank.customer.domain.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindCustomersUseCase {
    private final CustomerRepository customerRepository;

    public FindCustomersUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<FindCustomersResponse> execute(Pageable pageable) {

        return customerRepository.search(pageable)
                .map(
                        c -> new FindCustomersResponse(
                                c.getId(),
                                c.getCustomerName().firstName(),
                                c.getCustomerName().lastName(),
                                c.getEmail().value(),
                                c.getStatus()
                        )
                );
    }
}
