package com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDetails {
    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;
} 