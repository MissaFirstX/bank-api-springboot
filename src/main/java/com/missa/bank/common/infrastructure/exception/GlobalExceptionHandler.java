package com.missa.bank.common.infrastructure.exception;

import com.missa.bank.common.infrastructure.dto.ValidationErrorResponse;
import com.missa.bank.common.infrastructure.dto.FieldErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request
    ) {

        HttpStatus status = ex.getStatus();

        return ResponseEntity.status(status).body(
                new ErrorResponse(
                        LocalDateTime.now(),
                        status.value(),
                        status.getReasonPhrase(),
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMessageNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(
                new ErrorResponse(
                        LocalDateTime.now(),
                        status.value(),
                        status.getReasonPhrase(),
                        "Required request body is missing.",
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        List<FieldErrorResponse> errors =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error -> new FieldErrorResponse(
                                error.getField(),
                                error.getDefaultMessage()
                        ))
                        .toList();

        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(
                new ValidationErrorResponse(
                        LocalDateTime.now(),
                        status.value(),
                        status.getReasonPhrase(),
                        "Validation failed.",
                        request.getRequestURI(),
                        errors
                )
        );
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(
            Exception ex,
            HttpServletRequest request) {

        System.out.println(ex.getClass() + " | " + ex.getMessage());

        return new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected error occurred.",
                request.getRequestURI()
        );
    }
}
