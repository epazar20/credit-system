package com.banking.repositories.abstracts;

import com.banking.entities.IndividualLoanType;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualLoanTypeRepository extends LoanTypeRepository<IndividualLoanType> {
} 