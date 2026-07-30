package com.missa.bank.security.application.usecase;

import com.missa.bank.security.api.request.LoginRequest;
import com.missa.bank.security.api.response.LoginResponse;
import com.missa.bank.security.domain.exception.InvalidCredentialsException;
import com.missa.bank.security.domain.exception.UserDisabledException;
import com.missa.bank.security.domain.exception.UserNotFoundException;
import com.missa.bank.security.domain.model.User;
import com.missa.bank.security.domain.repository.UserRepository;
import com.missa.bank.security.domain.valueobject.Username;
import com.missa.bank.security.infrastructure.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse execute(LoginRequest request) {
        Username username = new Username(request.username());
        User user = userRepository.findByUserName(username.value())
                .orElseThrow(
                        () -> new UserNotFoundException("User not found")
                );

        if (!user.isEnabled()) {
            throw new UserDisabledException("User is not enabled");
        }

        if (!passwordEncoder.matches(request.password(), user.getPassword().value())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                "Bearer",
                user.getId(),
                user.getUsername().value(),
                user.getRole().name()
        );

    }


}
