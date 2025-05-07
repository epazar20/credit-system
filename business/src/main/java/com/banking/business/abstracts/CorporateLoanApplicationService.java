package com.banking.business.abstracts;

import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateCorporateLoanApplicationRequest;
import com.banking.business.dtos.responses.CorporateLoanApplicationResponse;

public interface CorporateLoanApplicationService {
    PageResponse<CorporateLoanApplicationResponse> getAll(int page, int size, String sortBy, String direction);
    CorporateLoanApplicationResponse getById(Long id);
    CorporateLoanApplicationResponse create(CreateCorporateLoanApplicationRequest request);
    void delete(Long id);
    CorporateLoanApplicationResponse evaluate(Long id);
} 