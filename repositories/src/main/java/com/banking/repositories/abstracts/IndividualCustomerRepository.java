package com.banking.repositories.abstracts;

import com.banking.entities.IndividualCustomer;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IndividualCustomerRepository extends CustomerRepository<IndividualCustomer> {
    boolean existsByNationalId(String nationalId);
    Optional<IndividualCustomer> findByNationalId(String nationalId);
} 