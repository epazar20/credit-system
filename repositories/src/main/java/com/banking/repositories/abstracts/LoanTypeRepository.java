package com.banking.repositories.abstracts;

import com.banking.entities.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTypeRepository<T extends LoanType> extends JpaRepository<T, Long> {
    boolean existsByName(String name);
} 