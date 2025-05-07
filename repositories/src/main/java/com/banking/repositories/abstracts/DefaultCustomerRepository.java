package com.banking.repositories.abstracts;

import com.banking.entities.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultCustomerRepository extends CustomerRepository<Customer> {
} 