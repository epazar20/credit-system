package com.banking.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanTypeResponse {
    private Long id;
    private String name;
    private String description;
    private Double minAmount;
    private Double maxAmount;
    private Double baseInterestRate;
    private Integer minTermMonths;
    private Integer maxTermMonths;
    private String type;
} 