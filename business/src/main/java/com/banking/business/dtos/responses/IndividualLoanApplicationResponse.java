package com.banking.business.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class IndividualLoanApplicationResponse extends LoanApplicationResponse {
    private Long customerId;
    private String customerFullName;
    private Integer creditScore;
    private Double monthlyIncome;
    private Integer existingLoanCount;
    private Double totalExistingLoanPayments;
    private Double debtToIncomeRatio;
} 