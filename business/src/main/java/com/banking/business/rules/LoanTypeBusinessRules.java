package com.banking.business.rules;

import com.banking.core.exceptions.BusinessException;
import com.banking.repositories.abstracts.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LoanTypeBusinessRules {
    private final LoanTypeRepository<?> repository;

    public LoanTypeBusinessRules(@Qualifier("loanTypeRepository") LoanTypeRepository<?> repository) {
        this.repository = repository;
    }

    public void checkIfNameExists(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Loan type with name " + name + " already exists");
        }
    }

    public void checkIfAmountRangeIsValid(Double minAmount, Double maxAmount) {
        if (minAmount >= maxAmount) {
            throw new BusinessException("Minimum amount must be less than maximum amount");
        }
    }

    public void checkIfTermRangeIsValid(Integer minTermMonths, Integer maxTermMonths) {
        if (minTermMonths >= maxTermMonths) {
            throw new BusinessException("Minimum term must be less than maximum term");
        }
    }
} 