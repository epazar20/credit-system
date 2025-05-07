package com.banking.controller;

import com.banking.business.abstracts.IndividualCustomerService;
import com.banking.business.dtos.requests.CreateIndividualCustomerRequest;
import com.banking.business.dtos.requests.UpdateIndividualCustomerRequest;
import com.banking.business.dtos.responses.IndividualCustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/individual-customers")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Individual Customers", description = "Operations for individual customers")
public class IndividualCustomersController {
    private final IndividualCustomerService individualCustomerService;

    @GetMapping
    @Operation(summary = "Get all individual customers")
    public List<IndividualCustomerResponse> getAll() {
        return individualCustomerService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an individual customer by ID")
    public IndividualCustomerResponse getById(@PathVariable Long id) {
        return individualCustomerService.getById(id);
    }

    @GetMapping("/by-national-id/{nationalId}")
    @Operation(summary = "Get an individual customer by national ID")
    public IndividualCustomerResponse getByNationalId(@PathVariable String nationalId) {
        return individualCustomerService.getByNationalId(nationalId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new individual customer")
    public IndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest request) {
        return individualCustomerService.add(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing individual customer")
    public IndividualCustomerResponse update(@PathVariable Long id, @Valid @RequestBody UpdateIndividualCustomerRequest request) {
        return individualCustomerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an individual customer")
    public void delete(@PathVariable Long id) {
        individualCustomerService.delete(id);
    }
} 