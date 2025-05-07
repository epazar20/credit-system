package com.banking.business.abstracts;

import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateIndividualLoanTypeRequest;
import com.banking.business.dtos.responses.IndividualLoanTypeResponse;

public interface IndividualLoanTypeService {
    PageResponse<IndividualLoanTypeResponse> getAll(int page, int size, String sortBy, String direction);
    IndividualLoanTypeResponse getById(Long id);
    IndividualLoanTypeResponse create(CreateIndividualLoanTypeRequest request);
    void delete(Long id);
} 