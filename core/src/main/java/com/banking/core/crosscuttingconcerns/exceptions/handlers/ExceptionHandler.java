package com.banking.core.crosscuttingconcerns.exceptions.handlers;

import com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails.ProblemDetails;

public abstract class ExceptionHandler {
    public abstract ProblemDetails handleException(Exception exception);
} 