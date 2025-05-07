package com.banking.business.dtos.responses;

import com.banking.entities.enums.LoanApplicationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class LoanApplicationResponse {
    private Long id;
    private Long loanTypeId;
    private String loanTypeName;
    private Double requestedAmount;
    private Integer termMonths;
    private Double interestRate;
    private Double monthlyPayment;
    private Double totalPayment;
    private LoanApplicationStatus status;
    private LocalDateTime decisionDate;
    private String rejectionReason;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
} 