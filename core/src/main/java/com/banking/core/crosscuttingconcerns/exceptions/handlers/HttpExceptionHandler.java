package com.banking.core.crosscuttingconcerns.exceptions.handlers;

import com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails.BusinessProblemDetails;
import com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails.ProblemDetails;
import com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails.ValidationProblemDetails;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HttpExceptionHandler extends com.banking.core.crosscuttingconcerns.exceptions.handlers.ExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpExceptionHandler.class);

    @Override
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetails handleException(Exception exception) {
        logger.error("Unexpected error occurred: {}", exception.getMessage(), exception);
        
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setTitle("Internal Server Error");
        problemDetails.setDetail(exception.getMessage());
        problemDetails.setType("https://example.com/probs/internal");
        problemDetails.setStatus(500);
        return problemDetails;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException exception) {
        logger.warn("Business rule violation: {}", exception.getMessage());
        return new BusinessProblemDetails(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        logger.warn("Validation error occurred");
        
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            logger.warn("Validation error for field '{}': {}", fieldError.getField(), fieldError.getDefaultMessage());
        }
        
        return new ValidationProblemDetails(validationErrors);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(ValidationException exception) {
        logger.warn("Validation error: {}", exception.getMessage());
        
        Map<String, String> validationErrors = new HashMap<>();
        validationErrors.put("validation", exception.getMessage());
        return new ValidationProblemDetails(validationErrors);
    }
} 