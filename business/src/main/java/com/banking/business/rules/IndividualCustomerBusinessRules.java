package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {
    private final IndividualCustomerRepository individualCustomerRepository;

    public void checkIfNationalIdExists(String nationalId) {
        if (individualCustomerRepository.existsByNationalId(nationalId)) {
            throw new BusinessException(Messages.NATIONAL_ID_EXISTS);
        }
    }
} 