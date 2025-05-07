package com.banking.business.concretes;

import com.banking.business.abstracts.IndividualCustomerService;
import com.banking.business.constants.Messages;
import com.banking.business.dtos.requests.CreateIndividualCustomerRequest;
import com.banking.business.dtos.requests.UpdateIndividualCustomerRequest;
import com.banking.business.dtos.responses.IndividualCustomerResponse;
import com.banking.business.mappings.IndividualCustomerMapper;
import com.banking.business.rules.CustomerBusinessRules;
import com.banking.business.rules.IndividualCustomerBusinessRules;
import com.banking.entities.IndividualCustomer;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class IndividualCustomerManager implements IndividualCustomerService {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private final CustomerBusinessRules customerBusinessRules;
    private final IndividualCustomerMapper mapper;

    @Override
    public List<IndividualCustomerResponse> getAll() {
        List<IndividualCustomer> customers = individualCustomerRepository.findAll();
        return mapper.individualCustomersToResponses(customers);
    }

    @Override
    public IndividualCustomerResponse getById(Long id) {
        IndividualCustomer customer = individualCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Messages.INDIVIDUAL_CUSTOMER_NOT_FOUND));
        return mapper.individualCustomerToResponse(customer);
    }

    @Override
    public IndividualCustomerResponse getByNationalId(String nationalId) {
        IndividualCustomer customer = individualCustomerRepository.findByNationalId(nationalId)
                .orElseThrow(() -> new RuntimeException(Messages.INDIVIDUAL_CUSTOMER_NOT_FOUND));
        return mapper.individualCustomerToResponse(customer);
    }

    @Override
    public IndividualCustomerResponse add(CreateIndividualCustomerRequest request) {
        customerBusinessRules.checkIfEmailExists(request.email());
        customerBusinessRules.checkIfPhoneNumberExists(request.phoneNumber());
        individualCustomerBusinessRules.checkIfNationalIdExists(request.nationalId());

        IndividualCustomer customer = mapper.createIndividualCustomerRequestToIndividualCustomer(request);
        customer.setCreatedDate(LocalDateTime.now());
        
        IndividualCustomer savedCustomer = individualCustomerRepository.save(customer);
        return mapper.individualCustomerToResponse(savedCustomer);
    }

    @Override
    public IndividualCustomerResponse update(Long id, UpdateIndividualCustomerRequest request) {
        IndividualCustomer existingCustomer = individualCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Messages.INDIVIDUAL_CUSTOMER_NOT_FOUND));
        
        if (!existingCustomer.getEmail().equals(request.email())) {
            customerBusinessRules.checkIfEmailExists(request.email());
        }
        
        if (!existingCustomer.getPhoneNumber().equals(request.phoneNumber())) {
            customerBusinessRules.checkIfPhoneNumberExists(request.phoneNumber());
        }
        
        if (!existingCustomer.getNationalId().equals(request.nationalId())) {
            individualCustomerBusinessRules.checkIfNationalIdExists(request.nationalId());
        }

        IndividualCustomer customer = mapper.updateIndividualCustomerRequestToIndividualCustomer(request);
        customer.setId(id);
        customer.setCreatedDate(existingCustomer.getCreatedDate());
        customer.setUpdatedDate(LocalDateTime.now());
        
        IndividualCustomer updatedCustomer = individualCustomerRepository.save(customer);
        return mapper.individualCustomerToResponse(updatedCustomer);
    }

    @Override
    public void delete(Long id) {
        IndividualCustomer customer = individualCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Messages.INDIVIDUAL_CUSTOMER_NOT_FOUND));
        customer.setDeletedDate(LocalDateTime.now());
        individualCustomerRepository.save(customer);
    }
} 