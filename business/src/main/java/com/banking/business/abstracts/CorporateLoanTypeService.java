package com.banking.business.abstracts;

import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateCorporateLoanTypeRequest;
import com.banking.business.dtos.responses.CorporateLoanTypeResponse;

public interface CorporateLoanTypeService {
    PageResponse<CorporateLoanTypeResponse> getAll(int page, int size, String sortBy, String direction);
    CorporateLoanTypeResponse getById(Long id);
    CorporateLoanTypeResponse create(CreateCorporateLoanTypeRequest request);
    void delete(Long id);
} 