package com.banking.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorporateLoanTypeResponse extends LoanTypeResponse {
    private Double minAnnualRevenue;
    private Integer minYearsInBusiness;
    private Boolean requiresCollateral;
} 