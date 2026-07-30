package com.missa.bank.common.infrastructure.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<FieldErrorResponse> errors
) {}