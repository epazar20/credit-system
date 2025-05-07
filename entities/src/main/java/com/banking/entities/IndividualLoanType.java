package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "individual_loan_types")
@PrimaryKeyJoinColumn(name = "loan_type_id")
public class IndividualLoanType extends LoanType {
    
    @Column(name = "min_credit_score", nullable = false)
    private Integer minCreditScore;
    
    @Column(name = "max_age", nullable = false)
    private Integer maxAge;
    
    @Column(name = "min_monthly_income", nullable = false)
    private Double minMonthlyIncome;
} 