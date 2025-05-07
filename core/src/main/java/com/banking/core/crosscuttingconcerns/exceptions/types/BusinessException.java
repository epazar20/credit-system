package com.banking.core.crosscuttingconcerns.exceptions.types;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
} 