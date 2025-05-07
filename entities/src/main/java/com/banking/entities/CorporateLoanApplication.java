package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity
@Getter
@Setter
@Table(name = "corporate_loan_applications")
@PrimaryKeyJoinColumn(name = "loan_application_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CorporateLoanApplication extends LoanApplication {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CorporateCustomer customer;
    
    @Column(name = "annual_revenue", nullable = false)
    private Double annualRevenue;
    
    @Column(name = "monthly_revenue", nullable = false)
    private Double monthlyRevenue;
    
    @Column(name = "years_in_business", nullable = false)
    private Integer yearsInBusiness;
    
    @Column(name = "collateral_value")
    private Double collateralValue;
    
    @Column(name = "collateral_type")
    private String collateralType;
    
    @Column(name = "existing_loan_count")
    private Integer existingLoanCount;
    
    @Column(name = "total_existing_loan_payments")
    private Double totalExistingLoanPayments;
} 