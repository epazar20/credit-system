package com.banking.controller;

import com.banking.business.abstracts.CustomerService;
import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.responses.CorporateCustomerResponse;
import com.banking.business.dtos.responses.IndividualCustomerResponse;
import com.banking.entities.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Customers", description = "Combined operations for all customer types")
public class CustomersController {
    private final CustomerService<Customer> customerService;

    @GetMapping("/individual")
    @Operation(summary = "Get all individual customers with pagination")
    public PageResponse<IndividualCustomerResponse> getAllIndividualCustomers(
            @Parameter(description = "Page number (0-based)")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field")
            @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Sort direction (asc/desc)")
            @RequestParam(defaultValue = "asc") String direction) {
        return customerService.getAllIndividualCustomers(page, size, sortBy, direction);
    }

    @GetMapping("/corporate")
    @Operation(summary = "Get all corporate customers with pagination")
    public PageResponse<CorporateCustomerResponse> getAllCorporateCustomers(
            @Parameter(description = "Page number (0-based)")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field")
            @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Sort direction (asc/desc)")
            @RequestParam(defaultValue = "asc") String direction) {
        return customerService.getAllCorporateCustomers(page, size, sortBy, direction);
    }
} 