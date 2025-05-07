package com.banking.repositories.abstracts;

import com.banking.entities.CorporateLoanType;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateLoanTypeRepository extends LoanTypeRepository<CorporateLoanType> {
} 