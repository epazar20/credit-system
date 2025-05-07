package com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails(String detail) {
        setTitle("Business Rule Violation");
        setDetail(detail);
        setType("https://example.com/probs/business");
        setStatus(400);
    }
} 