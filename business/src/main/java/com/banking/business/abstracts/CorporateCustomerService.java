package com.banking.business.abstracts;

import com.banking.business.dtos.requests.CreateCorporateCustomerRequest;
import com.banking.business.dtos.requests.UpdateCorporateCustomerRequest;
import com.banking.business.dtos.responses.CorporateCustomerResponse;

import java.util.List;

public interface CorporateCustomerService {
    List<CorporateCustomerResponse> getAll();
    CorporateCustomerResponse getById(Long id);
    CorporateCustomerResponse getByTaxNumber(String taxNumber);
    CorporateCustomerResponse add(CreateCorporateCustomerRequest request);
    CorporateCustomerResponse update(Long id, UpdateCorporateCustomerRequest request);
    void delete(Long id);
} 