package com.banking.business.concretes;

import com.banking.business.abstracts.CorporateCustomerService;
import com.banking.business.constants.Messages;
import com.banking.business.dtos.requests.CreateCorporateCustomerRequest;
import com.banking.business.dtos.requests.UpdateCorporateCustomerRequest;
import com.banking.business.dtos.responses.CorporateCustomerResponse;
import com.banking.business.mappings.CorporateCustomerMapper;
import com.banking.business.rules.CustomerBusinessRules;
import com.banking.business.rules.CorporateCustomerBusinessRules;
import com.banking.entities.CorporateCustomer;
import com.banking.repositories.abstracts.CorporateCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CorporateCustomerManager implements CorporateCustomerService {
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final CorporateCustomerBusinessRules corporateCustomerBusinessRules;
    private final CustomerBusinessRules customerBusinessRules;
    private final CorporateCustomerMapper mapper;

    @Override
    public List<CorporateCustomerResponse> getAll() {
        List<CorporateCustomer> customers = corporateCustomerRepository.findAll();
        return mapper.corporateCustomersToResponses(customers);
    }

    @Override
    public CorporateCustomerResponse getById(Long id) {
        CorporateCustomer customer = corporateCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Messages.CORPORATE_CUSTOMER_NOT_FOUND));
        return mapper.corporateCustomerToResponse(customer);
    }

    @Override
    public CorporateCustomerResponse getByTaxNumber(String taxNumber) {
        CorporateCustomer customer = corporateCustomerRepository.findByTaxNumber(taxNumber)
                .orElseThrow(() -> new RuntimeException(Messages.CORPORATE_CUSTOMER_NOT_FOUND));
        return mapper.corporateCustomerToResponse(customer);
    }

    @Override
    public CorporateCustomerResponse add(CreateCorporateCustomerRequest request) {
        customerBusinessRules.checkIfEmailExists(request.email());
        customerBusinessRules.checkIfPhoneNumberExists(request.phoneNumber());
        corporateCustomerBusinessRules.checkIfTaxNumberExists(request.taxNumber());
        corporateCustomerBusinessRules.checkIfCompanyNameExists(request.companyName());

        CorporateCustomer customer = mapper.createCorporateCustomerRequestToCorporateCustomer(request);
        customer.setCreatedDate(LocalDateTime.now());
        
        CorporateCustomer savedCustomer = corporateCustomerRepository.save(customer);
        return mapper.corporateCustomerToResponse(savedCustomer);
    }

    @Override
    public CorporateCustomerResponse update(Long id, UpdateCorporateCustomerRequest request) {
        CorporateCustomer existingCustomer = corporateCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Messages.CORPORATE_CUSTOMER_NOT_FOUND));
        
        if (!existingCustomer.getEmail().equals(request.email())) {
            customerBusinessRules.checkIfEmailExists(request.email());
        }
        
        if (!existingCustomer.getPhoneNumber().equals(request.phoneNumber())) {
            customerBusinessRules.checkIfPhoneNumberExists(request.phoneNumber());
        }
        
        if (!existingCustomer.getTaxNumber().equals(request.taxNumber())) {
            corporateCustomerBusinessRules.checkIfTaxNumberExists(request.taxNumber());
        }
        
        if (!existingCustomer.getCompanyName().equals(request.companyName())) {
            corporateCustomerBusinessRules.checkIfCompanyNameExists(request.companyName());
        }

        CorporateCustomer customer = mapper.updateCorporateCustomerRequestToCorporateCustomer(request);
        customer.setId(id);
        customer.setCreatedDate(existingCustomer.getCreatedDate());
        customer.setUpdatedDate(LocalDateTime.now());
        
        CorporateCustomer updatedCustomer = corporateCustomerRepository.save(customer);
        return mapper.corporateCustomerToResponse(updatedCustomer);
    }

    @Override
    public void delete(Long id) {
        CorporateCustomer customer = corporateCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Messages.CORPORATE_CUSTOMER_NOT_FOUND));
        customer.setDeletedDate(LocalDateTime.now());
        corporateCustomerRepository.save(customer);
    }
} 