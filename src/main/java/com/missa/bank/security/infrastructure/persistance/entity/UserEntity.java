package com.missa.bank.security.infrastructure.persistance.entity;

import com.missa.bank.security.domain.valueobject.Password;
import com.missa.bank.security.domain.valueobject.Role;
import com.missa.bank.security.domain.valueobject.Username;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;
    private Long customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
