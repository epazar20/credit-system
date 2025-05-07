package com.banking.business.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCorporateLoanTypeRequest extends CreateLoanTypeRequest {
    @NotNull(message = "Minimum annual revenue is required")
    @Positive(message = "Minimum annual revenue must be positive")
    private Double minAnnualRevenue;
    
    @NotNull(message = "Minimum years in business is required")
    @Positive(message = "Minimum years in business must be positive")
    private Integer minYearsInBusiness;
    
    @NotNull(message = "Requires collateral field is required")
    private Boolean requiresCollateral;
} 