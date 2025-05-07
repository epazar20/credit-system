package com.banking.business.abstracts;

import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateCorporateLoanApplicationRequest;
import com.banking.business.dtos.requests.CreateIndividualLoanApplicationRequest;
import com.banking.business.dtos.responses.CorporateLoanApplicationResponse;
import com.banking.business.dtos.responses.IndividualLoanApplicationResponse;
import com.banking.business.dtos.responses.LoanApplicationResponse;
import com.banking.entities.LoanApplication;

import java.util.List;

public interface LoanApplicationService<T extends LoanApplication, R extends LoanApplicationResponse> {
    PageResponse<R> getAll(int page, int size, String sortBy, String direction);
    R getById(Long id);
    void delete(Long id);
    R evaluate(Long id);
    
    // Specific methods for individual and corporate loan applications
    List<IndividualLoanApplicationResponse> getAllIndividualLoanApplications();
    List<CorporateLoanApplicationResponse> getAllCorporateLoanApplications();
    IndividualLoanApplicationResponse getIndividualLoanApplicationById(Long id);
    CorporateLoanApplicationResponse getCorporateLoanApplicationById(Long id);
    IndividualLoanApplicationResponse createIndividualLoanApplication(CreateIndividualLoanApplicationRequest request);
    CorporateLoanApplicationResponse createCorporateLoanApplication(CreateCorporateLoanApplicationRequest request);
    void deleteIndividualLoanApplication(Long id);
    void deleteCorporateLoanApplication(Long id);
} 