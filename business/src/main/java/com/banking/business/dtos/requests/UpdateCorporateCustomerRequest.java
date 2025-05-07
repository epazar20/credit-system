package com.banking.business.dtos.requests;

import jakarta.validation.constraints.*;

public record UpdateCorporateCustomerRequest(
    @NotBlank(message = "Company name is required")
    String companyName,
    
    @NotBlank(message = "Tax number is required")
    @Size(min = 10, max = 10, message = "Tax number must be 10 characters")
    String taxNumber,
    
    @NotBlank(message = "Contact person name is required")
    String contactPersonName,
    
    String contactPersonTitle,
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email,
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    String phoneNumber,
    
    @NotBlank(message = "Address is required")
    String address
) {} 