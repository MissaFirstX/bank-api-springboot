package com.missa.bank.transaction.api.controller;

import com.missa.bank.transaction.api.request.CreateTransactionRequest;
import com.missa.bank.transaction.api.response.CreateTransactionResponse;
import com.missa.bank.transaction.api.response.FindTransactionResponse;
import com.missa.bank.transaction.application.usecase.CreateTransactionUseCase;
import com.missa.bank.transaction.application.usecase.FindTransactionByIdUseCase;
import com.missa.bank.transaction.application.usecase.FindTransactionsUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final CreateTransactionUseCase createTransactionUseCase;
    private final FindTransactionByIdUseCase findTransactionByIdUseCase;
    private final FindTransactionsUseCase findTransactionsUseCase;

    @PostMapping("/transfer")
    public CreateTransactionResponse transfer(@Valid @RequestBody CreateTransactionRequest request) {
        return createTransactionUseCase.execute(request);
    }

    @GetMapping("/{id}")
    public FindTransactionResponse findById(@PathVariable Long id) {
        return findTransactionByIdUseCase.execute(id);
    }

    @GetMapping
    public Page<FindTransactionResponse> search(@PageableDefault(page = 0, size = 20, sort = "createdAt") Pageable pageable) {
        return findTransactionsUseCase.execute(pageable);

    }
}
