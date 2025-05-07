package com.banking.business.abstracts;

import com.banking.business.dtos.requests.CreateIndividualCustomerRequest;
import com.banking.business.dtos.requests.UpdateIndividualCustomerRequest;
import com.banking.business.dtos.responses.IndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    List<IndividualCustomerResponse> getAll();
    IndividualCustomerResponse getById(Long id);
    IndividualCustomerResponse getByNationalId(String nationalId);
    IndividualCustomerResponse add(CreateIndividualCustomerRequest request);
    IndividualCustomerResponse update(Long id, UpdateIndividualCustomerRequest request);
    void delete(Long id);
} 