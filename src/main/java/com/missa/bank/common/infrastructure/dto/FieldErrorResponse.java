package com.missa.bank.common.infrastructure.dto;

import java.io.Serializable;

public record FieldErrorResponse(String field, String message) {
}
