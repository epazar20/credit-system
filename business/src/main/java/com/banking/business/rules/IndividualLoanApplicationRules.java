package com.banking.business.rules;

import com.banking.core.exceptions.BusinessException;
import com.banking.entities.IndividualCustomer;
import com.banking.entities.IndividualLoanApplication;
import com.banking.entities.IndividualLoanType;
import org.springframework.stereotype.Component;

@Component
public class IndividualLoanApplicationRules {
    
    public void checkIfCustomerEligibleForLoan(IndividualCustomer customer, IndividualLoanType loanType, 
                                             Double requestedAmount, Integer creditScore, Double monthlyIncome,
                                             Double existingMonthlyPayments) {
        checkCreditScore(creditScore, loanType.getMinCreditScore());
        checkAge(customer);
        checkMonthlyIncome(monthlyIncome, loanType.getMinMonthlyIncome());
        checkLoanAmount(requestedAmount, loanType.getMinAmount(), loanType.getMaxAmount());
        checkDebtToIncomeRatio(monthlyIncome, existingMonthlyPayments);
    }
    
    private void checkCreditScore(Integer creditScore, Integer minRequired) {
        if (creditScore < minRequired) {
            throw new BusinessException("Credit score is below minimum required: " + minRequired);
        }
    }
    
    private void checkAge(IndividualCustomer customer) {
        int age = customer.calculateAge();
        if (age < 18) {
            throw new BusinessException("Customer must be at least 18 years old");
        }
    }
    
    private void checkMonthlyIncome(Double monthlyIncome, Double minRequired) {
        if (monthlyIncome < minRequired) {
            throw new BusinessException("Monthly income is below minimum required: " + minRequired);
        }
    }
    
    private void checkLoanAmount(Double requestedAmount, Double minAmount, Double maxAmount) {
        if (requestedAmount < minAmount || requestedAmount > maxAmount) {
            throw new BusinessException("Requested amount must be between " + minAmount + " and " + maxAmount);
        }
    }
    
    private void checkDebtToIncomeRatio(Double monthlyIncome, Double existingMonthlyPayments) {
        double ratio = (existingMonthlyPayments != null ? existingMonthlyPayments : 0.0) / monthlyIncome;
        if (ratio > 0.5) {
            throw new BusinessException("Debt-to-income ratio exceeds maximum allowed (50%)");
        }
    }
    
    public void checkIfApplicationCanBeEvaluated(IndividualLoanApplication application) {
        if (application.getInterestRate() == null || application.getMonthlyPayment() == null) {
            throw new BusinessException("Application calculations must be completed before evaluation");
        }
    }
} 