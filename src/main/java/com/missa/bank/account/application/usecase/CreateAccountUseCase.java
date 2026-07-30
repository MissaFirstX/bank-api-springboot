package com.missa.bank.account.application.usecase;

import com.missa.bank.account.api.request.CreateAccountRequest;
import com.missa.bank.account.api.response.CreateAccountResponse;
import com.missa.bank.account.domain.model.Account;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.customer.domain.exception.CustomerNotFoundException;
import com.missa.bank.customer.domain.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public CreateAccountResponse execute(CreateAccountRequest request) {

        customerRepository.findById(request.customerId()).orElseThrow(
                () -> new CustomerNotFoundException("Customer ID not found")
        );

        Account account = new Account(request.accountType(), request.customerId());
        Account saved = accountRepository.save(account);

        return new CreateAccountResponse(
                saved.getId(),
                saved.getAccountNumber().accountNumber(),
                saved.getClabe().clabe(),
                saved.getCustomerId(),
                saved.getAccountStatus(),
                saved.getAccountType(),
                saved.getCreatedAt()
        );
    }
}
