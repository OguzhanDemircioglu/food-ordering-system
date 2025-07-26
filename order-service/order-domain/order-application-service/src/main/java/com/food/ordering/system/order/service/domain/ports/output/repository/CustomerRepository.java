package com.food.ordering.system.order.service.domain.ports.output.repository;

import entity.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);

    @Transactional
    Customer save(Customer customer);
}
