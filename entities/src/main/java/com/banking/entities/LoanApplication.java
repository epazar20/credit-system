package com.banking.entities;

import com.banking.entities.common.BaseEntity;
import com.banking.entities.enums.LoanApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "loan_applications")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LoanApplication extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_type_id", nullable = false)
    private LoanType loanType;
    
    @Column(name = "requested_amount", nullable = false)
    private Double requestedAmount;
    
    @Column(name = "term_months", nullable = false)
    private Integer termMonths;
    
    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;
    
    @Column(name = "monthly_payment")
    private Double monthlyPayment;
    
    @Column(name = "total_payment")
    private Double totalPayment;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanApplicationStatus status;
    
    @Column(name = "decision_date")
    private LocalDateTime decisionDate;
    
    @Column(name = "rejection_reason")
    private String rejectionReason;
} 