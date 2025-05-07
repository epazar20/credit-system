package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.repositories.abstracts.CorporateCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CorporateCustomerBusinessRules {
    private final CorporateCustomerRepository corporateCustomerRepository;

    public void checkIfTaxNumberExists(String taxNumber) {
        if (corporateCustomerRepository.existsByTaxNumber(taxNumber)) {
            throw new BusinessException(Messages.TAX_NUMBER_EXISTS);
        }
    }

    public void checkIfCompanyNameExists(String companyName) {
        if (corporateCustomerRepository.existsByCompanyName(companyName)) {
            throw new BusinessException(Messages.COMPANY_NAME_EXISTS);
        }
    }
} 