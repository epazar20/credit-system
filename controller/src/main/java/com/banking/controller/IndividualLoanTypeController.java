package com.banking.controller;

import com.banking.business.abstracts.IndividualLoanTypeService;
import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateIndividualLoanTypeRequest;
import com.banking.business.dtos.responses.IndividualLoanTypeResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/loan-types/individual")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class IndividualLoanTypeController {
    private final IndividualLoanTypeService service;

    @GetMapping
    public PageResponse<IndividualLoanTypeResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        return service.getAll(page, size, sortBy, direction);
    }

    @GetMapping("/{id}")
    public IndividualLoanTypeResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndividualLoanTypeResponse create(@Valid @RequestBody CreateIndividualLoanTypeRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
} 