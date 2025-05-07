package com.banking.business.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLoanTypeRequest {
    
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    
    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    @NotNull(message = "Minimum amount is required")
    @Positive(message = "Minimum amount must be positive")
    private Double minAmount;
    
    @NotNull(message = "Maximum amount is required")
    @Positive(message = "Maximum amount must be positive")
    private Double maxAmount;
    
    @NotNull(message = "Base interest rate is required")
    @PositiveOrZero(message = "Base interest rate cannot be negative")
    @DecimalMax(value = "100.0", message = "Base interest rate cannot exceed 100%")
    private Double baseInterestRate;
    
    @NotNull(message = "Minimum term months is required")
    @Positive(message = "Minimum term months must be positive")
    private Integer minTermMonths;
    
    @NotNull(message = "Maximum term months is required")
    @Positive(message = "Maximum term months must be positive")
    private Integer maxTermMonths;
} 