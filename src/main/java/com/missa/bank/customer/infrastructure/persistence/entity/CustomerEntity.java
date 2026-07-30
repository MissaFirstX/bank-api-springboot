package com.missa.bank.customer.infrastructure.persistence.entity;

import com.missa.bank.customer.domain.valueobject.CustomerStatus;
import com.missa.bank.customer.domain.valueobject.CustomerType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;


    private String curp;
    private String rfc;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
