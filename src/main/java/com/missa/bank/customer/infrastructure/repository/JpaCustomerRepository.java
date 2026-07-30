package com.missa.bank.customer.infrastructure.repository;

import com.missa.bank.customer.domain.model.Customer;
import com.missa.bank.customer.domain.repository.CustomerRepository;
import com.missa.bank.customer.infrastructure.persistence.entity.CustomerEntity;
import com.missa.bank.customer.infrastructure.persistence.mapper.CustomerPersistenceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaCustomerRepository implements CustomerRepository {
    private final SpringDataCustomerRepository repository;
    private final CustomerPersistenceMapper mapper;

    public JpaCustomerRepository(SpringDataCustomerRepository repository, CustomerPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Customer save(Customer customer) {

        CustomerEntity entity = mapper.toEntity(customer);

        CustomerEntity saved = repository.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(Long id) {

        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Customer> search( Pageable pageable) {

        Page<CustomerEntity> entities = repository.findAll(pageable);

        return entities.map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
