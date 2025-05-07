package com.banking.controller;

import com.banking.business.abstracts.CorporateLoanTypeService;
import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateCorporateLoanTypeRequest;
import com.banking.business.dtos.responses.CorporateLoanTypeResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/loan-types/corporate")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class CorporateLoanTypeController {
    private final CorporateLoanTypeService service;

    @GetMapping
    public PageResponse<CorporateLoanTypeResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        return service.getAll(page, size, sortBy, direction);
    }

    @GetMapping("/{id}")
    public CorporateLoanTypeResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CorporateLoanTypeResponse create(@Valid @RequestBody CreateCorporateLoanTypeRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
} 