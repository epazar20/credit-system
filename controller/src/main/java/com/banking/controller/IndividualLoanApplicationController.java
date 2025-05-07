package com.banking.controller;

import com.banking.business.abstracts.IndividualLoanApplicationService;
import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateIndividualLoanApplicationRequest;
import com.banking.business.dtos.responses.IndividualLoanApplicationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/loan-applications/individual")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class IndividualLoanApplicationController {
    private final IndividualLoanApplicationService service;

    @GetMapping
    public PageResponse<IndividualLoanApplicationResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        return service.getAll(page, size, sortBy, direction);
    }

    @GetMapping("/{id}")
    public IndividualLoanApplicationResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndividualLoanApplicationResponse create(@Valid @RequestBody CreateIndividualLoanApplicationRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}/evaluate")
    public IndividualLoanApplicationResponse evaluate(@PathVariable Long id) {
        return service.evaluate(id);
    }
} 