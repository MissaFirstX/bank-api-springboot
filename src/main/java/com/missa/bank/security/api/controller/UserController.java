package com.missa.bank.security.api.controller;

import com.missa.bank.security.api.request.LoginRequest;
import com.missa.bank.security.api.request.RegisterUserRequest;
import com.missa.bank.security.api.response.LoginResponse;
import com.missa.bank.security.api.response.RegisterUserResponse;
import com.missa.bank.security.application.usecase.LoginUseCase;
import com.missa.bank.security.application.usecase.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping("/register")
    public RegisterUserResponse register(@RequestBody RegisterUserRequest request) {
        return registerUserUseCase.execute(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return loginUseCase.execute(request);
    }
}
