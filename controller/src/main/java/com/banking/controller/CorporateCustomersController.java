package com.banking.controller;

import com.banking.business.abstracts.CorporateCustomerService;
import com.banking.business.dtos.requests.CreateCorporateCustomerRequest;
import com.banking.business.dtos.requests.UpdateCorporateCustomerRequest;
import com.banking.business.dtos.responses.CorporateCustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/corporate-customers")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Corporate Customers", description = "Operations for corporate customers")
public class CorporateCustomersController {
    private final CorporateCustomerService corporateCustomerService;

    @GetMapping
    @Operation(summary = "Get all corporate customers")
    public List<CorporateCustomerResponse> getAll() {
        return corporateCustomerService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a corporate customer by ID")
    public CorporateCustomerResponse getById(@PathVariable Long id) {
        return corporateCustomerService.getById(id);
    }

    @GetMapping("/by-tax-number/{taxNumber}")
    @Operation(summary = "Get a corporate customer by tax number")
    public CorporateCustomerResponse getByTaxNumber(@PathVariable String taxNumber) {
        return corporateCustomerService.getByTaxNumber(taxNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new corporate customer")
    public CorporateCustomerResponse add(@Valid @RequestBody CreateCorporateCustomerRequest request) {
        return corporateCustomerService.add(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing corporate customer")
    public CorporateCustomerResponse update(@PathVariable Long id, @Valid @RequestBody UpdateCorporateCustomerRequest request) {
        return corporateCustomerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a corporate customer")
    public void delete(@PathVariable Long id) {
        corporateCustomerService.delete(id);
    }
} 