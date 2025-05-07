package com.banking.repositories.abstracts;

import com.banking.entities.CorporateCustomer;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CorporateCustomerRepository extends CustomerRepository<CorporateCustomer> {
    boolean existsByTaxNumber(String taxNumber);
    boolean existsByCompanyName(String companyName);
    Optional<CorporateCustomer> findByTaxNumber(String taxNumber);
} 