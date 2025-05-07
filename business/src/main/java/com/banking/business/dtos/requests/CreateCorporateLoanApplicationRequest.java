package com.banking.business.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateLoanApplicationRequest {
    
    @NotNull(message = "Loan type ID is required")
    private Long loanTypeId;
    
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    
    @NotNull(message = "Requested amount is required")
    @Positive(message = "Requested amount must be positive")
    private Double requestedAmount;
    
    @NotNull(message = "Term months is required")
    @Min(value = 1, message = "Term months must be at least 1")
    private Integer termMonths;
    
    @NotNull(message = "Annual revenue is required")
    @Positive(message = "Annual revenue must be positive")
    private Double annualRevenue;
    
    @NotNull(message = "Monthly revenue is required")
    @Positive(message = "Monthly revenue must be positive")
    private Double monthlyRevenue;
    
    @NotNull(message = "Years in business is required")
    @Positive(message = "Years in business must be positive")
    private Integer yearsInBusiness;
    
    private Double collateralValue;
    
    private String collateralType;
    
    @Min(value = 0, message = "Existing loan count cannot be negative")
    private Integer existingLoanCount;
    
    @PositiveOrZero(message = "Total existing loan payments cannot be negative")
    private Double totalExistingLoanPayments;
} 