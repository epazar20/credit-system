package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.repositories.abstracts.DefaultCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private final DefaultCustomerRepository customerRepository;

    public void checkIfEmailExists(String email) {
        if (customerRepository.existsByEmail(email)) {
            throw new BusinessException(Messages.CUSTOMER_EMAIL_EXISTS);
        }
    }

    public void checkIfPhoneNumberExists(String phoneNumber) {
        if (customerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new BusinessException(Messages.CUSTOMER_PHONE_EXISTS);
        }
    }
} 