package com.banking.business.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CorporateLoanApplicationResponse extends LoanApplicationResponse {
    private Long customerId;
    private String customerCompanyName;
    private Double annualRevenue;
    private Double monthlyRevenue;
    private Integer yearsInBusiness;
    private Double loanAmount;
    private Integer loanTerm;
    private Double collateralValue;
    private String collateralType;
    private Integer existingLoanCount;
    private Double totalExistingLoanPayments;
    private LocalDateTime applicationDate;
    private String loanTypeName;
} 