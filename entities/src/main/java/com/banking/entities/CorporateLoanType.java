package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "corporate_loan_types")
@PrimaryKeyJoinColumn(name = "loan_type_id")
public class CorporateLoanType extends LoanType {
    
    @Column(name = "min_annual_revenue", nullable = false)
    private Double minAnnualRevenue;
    
    @Column(name = "min_years_in_business", nullable = false)
    private Integer minYearsInBusiness;
    
    @Column(name = "requires_collateral", nullable = false)
    private Boolean requiresCollateral;
} 