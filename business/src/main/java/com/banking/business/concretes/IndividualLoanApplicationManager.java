package com.banking.business.concretes;

import com.banking.business.abstracts.IndividualLoanApplicationService;
import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateIndividualLoanApplicationRequest;
import com.banking.business.dtos.responses.IndividualLoanApplicationResponse;
import com.banking.business.mappers.IndividualLoanApplicationMapper;
import com.banking.business.rules.IndividualLoanApplicationRules;
import com.banking.core.exceptions.BusinessException;
import com.banking.entities.IndividualCustomer;
import com.banking.entities.IndividualLoanApplication;
import com.banking.entities.IndividualLoanType;
import com.banking.entities.enums.LoanApplicationStatus;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import com.banking.repositories.abstracts.IndividualLoanApplicationRepository;
import com.banking.repositories.abstracts.IndividualLoanTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class IndividualLoanApplicationManager implements IndividualLoanApplicationService {
    private final IndividualLoanApplicationRepository applicationRepository;
    private final IndividualCustomerRepository customerRepository;
    private final IndividualLoanTypeRepository loanTypeRepository;
    private final IndividualLoanApplicationMapper mapper;
    private final IndividualLoanApplicationRules rules;

    @Override
    public PageResponse<IndividualLoanApplicationResponse> getAll(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        
        Page<IndividualLoanApplication> applicationPage = applicationRepository.findAll(pageRequest);
        
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
    public IndividualLoanApplicationResponse getById(Long id) {
        return mapper.toResponse(
            applicationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Loan application not found: " + id))
        );
    }

    @Override
    @Transactional
    public IndividualLoanApplicationResponse create(CreateIndividualLoanApplicationRequest request) {
        IndividualCustomer customer = customerRepository.findById(request.getCustomerId())
            .orElseThrow(() -> new BusinessException("Customer not found: " + request.getCustomerId()));
            
        IndividualLoanType loanType = loanTypeRepository.findById(request.getLoanTypeId())
            .orElseThrow(() -> new BusinessException("Loan type not found: " + request.getLoanTypeId()));
            
        rules.checkIfCustomerEligibleForLoan(
            customer, 
            loanType,
            request.getRequestedAmount(),
            request.getCreditScore(),
            request.getMonthlyIncome(),
            request.getTotalExistingLoanPayments()
        );
        
        IndividualLoanApplication application = mapper.toEntity(request, customer, loanType);
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
    public IndividualLoanApplicationResponse evaluate(Long id) {
        IndividualLoanApplication application = applicationRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Loan application not found: " + id));
            
        rules.checkIfApplicationCanBeEvaluated(application);
        
        // Implement your evaluation logic here
        // For now, we'll just approve if credit score is above 700
        if (application.getCreditScore() >= 700) {
            application.setStatus(LoanApplicationStatus.APPROVED);
        } else {
            application.setStatus(LoanApplicationStatus.REJECTED);
            application.setRejectionReason("Credit score below required threshold");
        }
        
        application.setDecisionDate(LocalDateTime.now());
        return mapper.toResponse(applicationRepository.save(application));
    }
    
    private void calculateLoanTerms(IndividualLoanApplication application) {
        IndividualLoanType loanType = (IndividualLoanType) application.getLoanType();
        
        // Calculate interest rate based on credit score
        double baseRate = loanType.getBaseInterestRate();
        int creditScore = application.getCreditScore();
        
        double interestRate = baseRate;
        if (creditScore >= 800) {
            interestRate = baseRate - 0.02; // -2%
        } else if (creditScore >= 750) {
            interestRate = baseRate - 0.01; // -1%
        } else if (creditScore < 650) {
            interestRate = baseRate + 0.02; // +2%
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