package com.banking.repositories.abstracts;

import com.banking.entities.IndividualLoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualLoanApplicationRepository extends JpaRepository<IndividualLoanApplication, Long> {
} 