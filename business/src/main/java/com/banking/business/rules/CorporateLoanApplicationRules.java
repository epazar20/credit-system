package com.banking.business.rules;

import com.banking.core.exceptions.BusinessException;
import com.banking.entities.CorporateCustomer;
import com.banking.entities.CorporateLoanApplication;
import com.banking.entities.CorporateLoanType;
import org.springframework.stereotype.Component;

@Component
public class CorporateLoanApplicationRules {
    
    public void checkIfCustomerEligibleForLoan(CorporateCustomer customer, CorporateLoanType loanType,
                                             Double requestedAmount, Double annualRevenue, Integer yearsInBusiness,
                                             Double collateralValue, Double existingAnnualPayments) {
        checkYearsInBusiness(yearsInBusiness, loanType.getMinYearsInBusiness());
        checkAnnualRevenue(annualRevenue, loanType.getMinAnnualRevenue());
        checkLoanAmount(requestedAmount, loanType.getMinAmount(), loanType.getMaxAmount());
        checkCollateral(loanType.getRequiresCollateral(), collateralValue, requestedAmount);
        checkDebtToRevenueRatio(annualRevenue, existingAnnualPayments);
    }
    
    private void checkYearsInBusiness(Integer yearsInBusiness, Integer minRequired) {
        if (yearsInBusiness < minRequired) {
            throw new BusinessException("Years in business is below minimum required: " + minRequired);
        }
    }
    
    private void checkAnnualRevenue(Double annualRevenue, Double minRequired) {
        if (annualRevenue < minRequired) {
            throw new BusinessException("Annual revenue is below minimum required: " + minRequired);
        }
    }
    
    private void checkLoanAmount(Double requestedAmount, Double minAmount, Double maxAmount) {
        if (requestedAmount < minAmount || requestedAmount > maxAmount) {
            throw new BusinessException("Requested amount must be between " + minAmount + " and " + maxAmount);
        }
    }
    
    private void checkCollateral(Boolean requiresCollateral, Double collateralValue, Double requestedAmount) {
        if (Boolean.TRUE.equals(requiresCollateral)) {
            if (collateralValue == null || collateralValue < requestedAmount) {
                throw new BusinessException("Collateral value must be at least equal to the requested amount");
            }
        }
    }
    
    private void checkDebtToRevenueRatio(Double annualRevenue, Double existingAnnualPayments) {
        double ratio = (existingAnnualPayments != null ? existingAnnualPayments : 0.0) / annualRevenue;
        if (ratio > 0.7) {
            throw new BusinessException("Debt-to-revenue ratio exceeds maximum allowed (70%)");
        }
    }
    
    public void checkIfApplicationCanBeEvaluated(CorporateLoanApplication application) {
        if (application.getInterestRate() == null || application.getMonthlyPayment() == null) {
            throw new BusinessException("Application calculations must be completed before evaluation");
        }
    }
} 