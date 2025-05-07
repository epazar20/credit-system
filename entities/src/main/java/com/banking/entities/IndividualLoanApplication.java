package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "individual_loan_applications")
@PrimaryKeyJoinColumn(name = "loan_application_id")
public class IndividualLoanApplication extends LoanApplication {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private IndividualCustomer customer;
    
    @Column(name = "credit_score", nullable = false)
    private Integer creditScore;
    
    @Column(name = "monthly_income", nullable = false)
    private Double monthlyIncome;
    
    @Column(name = "existing_loan_count")
    private Integer existingLoanCount;
    
    @Column(name = "total_existing_loan_payments")
    private Double totalExistingLoanPayments;
} 