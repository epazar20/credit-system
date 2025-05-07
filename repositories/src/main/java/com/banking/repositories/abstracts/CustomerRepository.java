package com.banking.repositories.abstracts;

import com.banking.entities.Customer;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomerRepository<T extends Customer> extends BaseRepository<T> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
} 