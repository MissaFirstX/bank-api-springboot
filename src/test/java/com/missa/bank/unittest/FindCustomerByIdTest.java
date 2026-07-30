package com.missa.bank.unittest;

import com.missa.bank.customer.api.response.FindCustomerByIdResponse;
import com.missa.bank.customer.application.usecase.FindCustomerByIdUseCase;
import com.missa.bank.customer.domain.model.Customer;
import com.missa.bank.customer.domain.repository.CustomerRepository;
import com.missa.bank.customer.domain.valueobject.*;
import com.missa.bank.common.domain.valueobject.Email;
import com.missa.bank.common.domain.valueobject.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindCustomerByIdTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    FindCustomerByIdUseCase findCustomerByIdUseCase;

    private Customer customer;
    private Long id;

    @BeforeEach
    void setup() {
        id = 1L;

        customer = new Customer(
                id,
                new CustomerName(
                        "Misael",
                        "Alejandro",
                        "Ramirez"
                ),
                new BirthDate(LocalDate.of(2000, 5, 20)),
                new Email("misael@test.com"),
                new PhoneNumber("477-123-4567"),
                new Curp("AAMH030424HGTRRGA6"),
                new Rfc("AAMH030424H12"),
                CustomerType.INDIVIDUAL,
                CustomerStatus.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }


    @Test
    void shouldFindActiveCustomerById() {
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        FindCustomerByIdResponse response =
                findCustomerByIdUseCase.execute(id);

        assertEquals(id, response.id());
        assertEquals("Misael Alejandro Ramirez", response.name());
        assertEquals(CustomerStatus.ACTIVE, response.status());

        verify(customerRepository).findById(id);

    }

}
