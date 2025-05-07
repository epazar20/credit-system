package com.banking.business.concretes;

import com.banking.business.abstracts.CorporateLoanApplicationService;
import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateCorporateLoanApplicationRequest;
import com.banking.business.dtos.responses.CorporateLoanApplicationResponse;
import com.banking.business.mappers.CorporateLoanApplicationMapper;
import com.banking.business.rules.CorporateLoanApplicationRules;
import com.banking.core.exceptions.BusinessException;
import com.banking.entities.CorporateCustomer;
import com.banking.entities.CorporateLoanApplication;
import com.banking.entities.CorporateLoanType;
import com.banking.entities.enums.LoanApplicationStatus;
import com.banking.repositories.abstracts.CorporateCustomerRepository;
import com.banking.repositories.abstracts.CorporateLoanApplicationRepository;
import com.banking.repositories.abstracts.CorporateLoanTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CorporateLoanApplicationManager implements CorporateLoanApplicationService {
    private final CorporateLoanApplicationRepository applicationRepository;
    private final CorporateCustomerRepository customerRepository;
    private final CorporateLoanTypeRepository loanTypeRepository;
    private final CorporateLoanApplicationMapper mapper;
    private final CorporateLoanApplicationRules rules;

    @Override
    public PageResponse<CorporateLoanApplicationResponse> getAll(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        
        Page<CorporateLoanApplication> applicationPage = applicationRepository.findAll(pageRequest);
        
        return new PageResponse<>(
            applicationPage.getContent().stream()
                .map(mapper::toResponse)
                .toList(),
            applicationPage.getNumber(),
            applicationPage.getSize(),
            applicationPage.getTotalElements(),
            applicationPage.getTotalPages(),
            applicationPage.isFirst(),
            applicationPage.isLast(),
            applicationPage.hasNext(),
            applicationPage.hasPrevious()
        );
    }

    @Override
    public CorporateLoanApplicationResponse getById(Long id) {
        return mapper.toResponse(
            applicationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Loan application not found: " + id))
        );
    }

    @Override
    @Transactional
    public CorporateLoanApplicationResponse create(CreateCorporateLoanApplicationRequest request) {
        CorporateCustomer customer = customerRepository.findById(request.getCustomerId())
            .orElseThrow(() -> new BusinessException("Customer not found: " + request.getCustomerId()));
            
        CorporateLoanType loanType = loanTypeRepository.findById(request.getLoanTypeId())
            .orElseThrow(() -> new BusinessException("Loan type not found: " + request.getLoanTypeId()));
            
        rules.checkIfCustomerEligibleForLoan(
            customer,
            loanType,
            request.getRequestedAmount(),
            request.getAnnualRevenue(),
            request.getYearsInBusiness(),
            request.getCollateralValue(),
            request.getTotalExistingLoanPayments() != null ? 
                request.getTotalExistingLoanPayments() * 12 : null
        );
        
        CorporateLoanApplication application = mapper.toEntity(request, customer, loanType);
        calculateLoanTerms(application);
        
        return mapper.toResponse(applicationRepository.save(application));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new BusinessException("Loan application not found: " + id);
        }
        applicationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CorporateLoanApplicationResponse evaluate(Long id) {
        CorporateLoanApplication application = applicationRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Loan application not found: " + id));
            
        rules.checkIfApplicationCanBeEvaluated(application);
        
        // Implement your evaluation logic here
        // For now, we'll approve if they have sufficient collateral and revenue
        boolean hasCollateral = application.getCollateralValue() != null && 
            application.getCollateralValue() >= application.getRequestedAmount();
            
        double revenueToLoanRatio = application.getRequestedAmount() / application.getAnnualRevenue();
        
        if (hasCollateral && revenueToLoanRatio <= 0.5) {
            application.setStatus(LoanApplicationStatus.APPROVED);
        } else {
            application.setStatus(LoanApplicationStatus.REJECTED);
            application.setRejectionReason(
                hasCollateral ? 
                "Requested amount too high compared to annual revenue" :
                "Insufficient collateral"
            );
        }
        
        application.setDecisionDate(LocalDateTime.now());
        return mapper.toResponse(applicationRepository.save(application));
    }
    
    private void calculateLoanTerms(CorporateLoanApplication application) {
        CorporateLoanType loanType = (CorporateLoanType) application.getLoanType();
        
        // Calculate interest rate based on business factors
        double baseRate = loanType.getBaseInterestRate();
        double interestRate = baseRate;
        
        // Adjust rate based on years in business
        if (application.getYearsInBusiness() >= 10) {
            interestRate -= 0.01; // -1% for established businesses
        }
        
        // Adjust rate based on collateral
        if (application.getCollateralValue() != null && 
            application.getCollateralValue() >= application.getRequestedAmount() * 1.5) {
            interestRate -= 0.005; // -0.5% for strong collateral
        }
        
        // Adjust rate based on revenue to loan ratio
        double revenueToLoanRatio = application.getRequestedAmount() / application.getAnnualRevenue();
        if (revenueToLoanRatio > 0.3) {
            interestRate += 0.01; // +1% for higher risk
        }
        
        application.setInterestRate(interestRate);
        
        // Calculate monthly payment using the loan amortization formula
        double principal = application.getRequestedAmount();
        int termMonths = application.getTermMonths();
        double monthlyRate = interestRate / 12;
        
        double monthlyPayment = principal * 
            (monthlyRate * Math.pow(1 + monthlyRate, termMonths)) /
            (Math.pow(1 + monthlyRate, termMonths) - 1);
            
        application.setMonthlyPayment(monthlyPayment);
        application.setTotalPayment(monthlyPayment * termMonths);
    }
} 