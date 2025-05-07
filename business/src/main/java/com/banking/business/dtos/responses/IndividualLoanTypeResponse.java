package com.banking.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualLoanTypeResponse extends LoanTypeResponse {
    private Integer minCreditScore;
    private Integer maxAge;
    private Double minMonthlyIncome;
} 