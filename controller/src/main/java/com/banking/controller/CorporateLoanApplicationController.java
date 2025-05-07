package com.banking.controller;

import com.banking.business.abstracts.CorporateLoanApplicationService;
import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateCorporateLoanApplicationRequest;
import com.banking.business.dtos.responses.CorporateLoanApplicationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/loan-applications/corporate")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class CorporateLoanApplicationController {
    private final CorporateLoanApplicationService service;

    @GetMapping
    public PageResponse<CorporateLoanApplicationResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        return service.getAll(page, size, sortBy, direction);
    }

    @GetMapping("/{id}")
    public CorporateLoanApplicationResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CorporateLoanApplicationResponse create(@Valid @RequestBody CreateCorporateLoanApplicationRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}/evaluate")
    public CorporateLoanApplicationResponse evaluate(@PathVariable Long id) {
        return service.evaluate(id);
    }
} 