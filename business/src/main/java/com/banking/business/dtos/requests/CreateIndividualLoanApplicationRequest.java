package com.banking.business.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIndividualLoanApplicationRequest {
    
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
    
    @NotNull(message = "Credit score is required")
    @Min(value = 0, message = "Credit score must be at least 0")
    @Max(value = 1000, message = "Credit score must be at most 1000")
    private Integer creditScore;
    
    @NotNull(message = "Monthly income is required")
    @Positive(message = "Monthly income must be positive")
    private Double monthlyIncome;
    
    @Min(value = 0, message = "Existing loan count cannot be negative")
    private Integer existingLoanCount;
    
    @PositiveOrZero(message = "Total existing loan payments cannot be negative")
    private Double totalExistingLoanPayments;
} 