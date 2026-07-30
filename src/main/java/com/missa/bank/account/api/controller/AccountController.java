package com.missa.bank.account.api.controller;

import com.missa.bank.account.api.request.CreateAccountRequest;
import com.missa.bank.account.api.request.DepositRequest;
import com.missa.bank.account.api.request.WithdrawRequest;
import com.missa.bank.account.api.response.*;
import com.missa.bank.account.application.usecase.*;
import com.missa.bank.account.domain.repository.AccountRepository;
import com.missa.bank.customer.application.usecase.FindCustomerByIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final FindAccountById findAccountById;
    private final FindAccountsUseCase findAccountsUseCase;
    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;
    private final FindTransactionsByAccountUseCase findTransactionsByAccountUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        return createAccountUseCase.execute(createAccountRequest);
    }

    @GetMapping("/{id}")
    public FindAccountByIdResponse findAccountById(@PathVariable Long id) {
        return findAccountById.execute(id);

    }

    @GetMapping
    public Page<FindAccountsResponse> findAccounts(@PageableDefault(page = 0, size = 2, sort = "customerId") Pageable pageable) {
        return findAccountsUseCase.execute(pageable);
    }

    @GetMapping("/{id}/transactions")
    public Page<FindTransactionsByAccountResponse> findTransactionsByAccount(@PathVariable Long id,
                                                                             @PageableDefault(page = 0, size = 2) Pageable pageable) {

        return findTransactionsByAccountUseCase.execute(id,pageable);

    }

    @PostMapping("/{id}/deposit")
    public DepositResponse deposit(@PathVariable Long id, @RequestBody DepositRequest request) {
        return depositUseCase.execute(id, request);
    }

    @PostMapping("/{id}/withdraw")
    public WithdrawResponse withdraw(@PathVariable Long id, @RequestBody WithdrawRequest request) {
        return withdrawUseCase.execute(id, request);
    }


}
