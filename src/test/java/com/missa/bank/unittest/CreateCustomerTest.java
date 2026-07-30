package com.missa.bank.unittest;

import com.missa.bank.customer.api.request.CreateCustomerRequest;
import com.missa.bank.customer.api.response.CreateCustomerResponse;
import com.missa.bank.customer.application.usecase.CreateCustomerUseCase;
import com.missa.bank.customer.domain.model.Customer;
import com.missa.bank.customer.domain.repository.CustomerRepository;
import com.missa.bank.customer.domain.valueobject.CustomerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CreateCustomerUseCase createCustomerUseCase;

    private CreateCustomerRequest request;

    @BeforeEach
    void setup() {
        request = new CreateCustomerRequest(
                "Misael",
                "Middle",
                "Lopez",
                LocalDate.of(2000, 1, 1),
                "misael@test.com",
                "555-123-4567",
                "AAMH030424HGTRRGA6",
                "AAMH030424F45",
                CustomerType.INDIVIDUAL
        );
    }

    @Test
    void shouldCreateCustomerSuccessfully() {

        // ARRANGE
        when(customerRepository.existsByEmail(request.email()))
                .thenReturn(false);

        when(customerRepository.save(any(Customer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        CreateCustomerResponse response = createCustomerUseCase.execute(request);

        // ASSERT
        assertNotNull(response);
        assertEquals("Misael Middle Lopez", response.name());

        verify(customerRepository).existsByEmail(request.email());
        verify(customerRepository).save(any(Customer.class));
    }
}
