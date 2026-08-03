package com.missa.bank.security.application.service;

import com.missa.bank.security.domain.model.User;
import com.missa.bank.security.infrastructure.security.SecurityUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    public User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (
                authentication == null
                        || !authentication.isAuthenticated()
        ) {
            throw new IllegalStateException(
                    "User is not authenticated"
            );
        }

        Object principal =
                authentication.getPrincipal();

        if (
                !(principal instanceof SecurityUserDetails userDetails)
        ) {
            throw new IllegalStateException(
                    "Invalid authenticated user"
            );
        }

        return userDetails.getUser();
    }
}