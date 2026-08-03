package com.missa.bank.security.application.usecase;

import com.missa.bank.customer.domain.exception.CustomerNotFoundException;
import com.missa.bank.customer.domain.model.Customer;
import com.missa.bank.customer.domain.repository.CustomerRepository;
import com.missa.bank.security.api.request.RegisterUserRequest;
import com.missa.bank.security.api.response.RegisterUserResponse;
import com.missa.bank.security.domain.exception.CustomerAlreadyHasUserException;
import com.missa.bank.security.domain.exception.InvalidUsernameException;
import com.missa.bank.security.domain.model.User;
import com.missa.bank.security.domain.repository.UserRepository;
import com.missa.bank.security.domain.valueobject.Password;
import com.missa.bank.security.domain.valueobject.Role;
import com.missa.bank.security.domain.valueobject.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final UserRepository repository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegisterUserResponse execute(RegisterUserRequest request) {
        Username username = new Username(request.username());

        if (repository.existsByUsername(username.value())) {
            throw new InvalidUsernameException("Username is already in use.");
        }

        if (request.role().equals(Role.CUSTOMER) && request.customerId() == null) {
            throw new InvalidUsernameException("No Customer id provided");
        }

        if (request.role().equals(Role.CUSTOMER)) {
            Customer customer = customerRepository
                    .findById(request.customerId())
                    .orElseThrow(
                            () -> new CustomerNotFoundException(
                                    "Customer not found"
                            )
                    );

            if (repository.existsByCustomerId(customer.getId())) {
                throw new CustomerAlreadyHasUserException("Customer already has a user");
            }
        }

        Long customerId = request.role().equals(Role.ADMIN) ? null : request.customerId();
        String encodedPassword = passwordEncoder.encode(request.password());

        User user = new User(
                username,
                new Password(encodedPassword),
                request.role(),
                customerId
        );

        User saved = repository.save(user);

        return new RegisterUserResponse(saved.getId(), saved.getUsername().value(), saved.getRole().name());
    }
}
