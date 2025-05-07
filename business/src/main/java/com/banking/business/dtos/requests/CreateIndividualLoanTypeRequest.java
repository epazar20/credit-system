package com.banking.business.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateIndividualLoanTypeRequest extends CreateLoanTypeRequest {
    @NotNull(message = "Minimum credit score is required")
    @Min(value = 0, message = "Minimum credit score cannot be negative")
    @Max(value = 850, message = "Maximum credit score cannot exceed 850")
    private Integer minCreditScore;
    
    @NotNull(message = "Maximum age is required")
    @Min(value = 18, message = "Maximum age must be at least 18")
    @Max(value = 120, message = "Maximum age cannot exceed 120")
    private Integer maxAge;
    
    @NotNull(message = "Minimum monthly income is required")
    @Positive(message = "Minimum monthly income must be positive")
    private Double minMonthlyIncome;
} 