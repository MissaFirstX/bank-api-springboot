package com.missa.bank.customer.api.controller;

import com.missa.bank.customer.api.request.CreateCustomerRequest;
import com.missa.bank.customer.api.request.UpdateCustomerEmailRequest;
import com.missa.bank.customer.api.request.UpdateCustomerPhoneNumberRequest;
import com.missa.bank.customer.api.response.*;
import com.missa.bank.customer.application.usecase.*;
import com.missa.bank.customer.domain.valueobject.CustomerStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final UpdateCustomerEmailUseCase updateCustomerEmailUseCase;
    private final UpdateCustomerPhoneNumberUseCase updateCustomerPhoneNumberUseCase;
    private final UpdateCustomerStatusUseCase updateCustomerStatusUseCase;
    private final FindCustomersUseCase findCustomersUseCase;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return createCustomerUseCase.execute(createCustomerRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FindCustomerByIdResponse findCustomerById(@Min(1) @PathVariable Long id) {
        return findCustomerByIdUseCase.execute(id);
    }

    @PatchMapping("/{id}/email")
    @ResponseStatus(HttpStatus.OK)
    public UpdateCustomerEmailResponse updateCustomerEmail(@PathVariable Long id, @Valid @RequestBody UpdateCustomerEmailRequest request) {
        return updateCustomerEmailUseCase.execute(id, request);
    }

    @PatchMapping("/{id}/phone")
    @ResponseStatus(HttpStatus.OK)
    public UpdateCustomerPhoneNumberResponse updatePhoneNumber(@PathVariable Long id, @Valid @RequestBody UpdateCustomerPhoneNumberRequest request) {
        return updateCustomerPhoneNumberUseCase.execute(id, request);
    }

    @PatchMapping("/{id}/status/{status}")
    public UpdateCustomerStatusResponse activate(@PathVariable Long id, @PathVariable CustomerStatus status) {
        return updateCustomerStatusUseCase.execute(id, status);
    }

    @GetMapping
    public Page<FindCustomersResponse> findAllCustomers(@PageableDefault(page = 0, size = 20, sort = "lastName") Pageable pageable) {
        return findCustomersUseCase.execute(pageable);
    }


}
