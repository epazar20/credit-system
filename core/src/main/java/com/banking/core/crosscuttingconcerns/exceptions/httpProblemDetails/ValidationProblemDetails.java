package com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationProblemDetails extends ProblemDetails {
    private Map<String, String> errors;

    public ValidationProblemDetails(Map<String, String> errors) {
        setTitle("Validation Rule Violation");
        setDetail("Validation errors occurred");
        setType("https://example.com/probs/validation");
        setStatus(400);
        setErrors(errors);
    }
} 