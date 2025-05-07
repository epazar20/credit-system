package com.banking.business.abstracts;

import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateIndividualLoanApplicationRequest;
import com.banking.business.dtos.responses.IndividualLoanApplicationResponse;

public interface IndividualLoanApplicationService {
    PageResponse<IndividualLoanApplicationResponse> getAll(int page, int size, String sortBy, String direction);
    IndividualLoanApplicationResponse getById(Long id);
    IndividualLoanApplicationResponse create(CreateIndividualLoanApplicationRequest request);
    void delete(Long id);
    IndividualLoanApplicationResponse evaluate(Long id);
} 