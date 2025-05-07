package com.banking.repositories.abstracts;

import com.banking.entities.CorporateLoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateLoanApplicationRepository extends JpaRepository<CorporateLoanApplication, Long> {
} 