package com.banking.entities;

import com.banking.entities.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "loan_types")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LoanType extends BaseEntity {
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @Column(name = "min_amount", nullable = false)
    private Double minAmount;
    
    @Column(name = "max_amount", nullable = false)
    private Double maxAmount;
    
    @Column(name = "base_interest_rate", nullable = false)
    private Double baseInterestRate;
    
    @Column(name = "min_term_months", nullable = false)
    private Integer minTermMonths;
    
    @Column(name = "max_term_months", nullable = false)
    private Integer maxTermMonths;
} 