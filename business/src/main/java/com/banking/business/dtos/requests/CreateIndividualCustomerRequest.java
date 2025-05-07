package com.banking.business.dtos.requests;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record CreateIndividualCustomerRequest(
    @NotBlank(message = "First name is required")
    String firstName,
    
    @NotBlank(message = "Last name is required")
    String lastName,
    
    @NotBlank(message = "National ID is required")
    @Size(min = 11, max = 11, message = "National ID must be 11 characters")
    String nationalId,
    
    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    LocalDate birthDate,
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email,
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    String phoneNumber,
    
    @NotBlank(message = "Address is required")
    String address
) {} 