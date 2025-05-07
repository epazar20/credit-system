package com.banking.business.concretes;

import com.banking.business.abstracts.IndividualLoanTypeService;
import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateIndividualLoanTypeRequest;
import com.banking.business.dtos.responses.IndividualLoanTypeResponse;
import com.banking.business.mappers.IndividualLoanTypeMapper;
import com.banking.business.rules.LoanTypeBusinessRules;
import com.banking.core.exceptions.BusinessException;
import com.banking.entities.IndividualLoanType;
import com.banking.repositories.abstracts.IndividualLoanTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class IndividualLoanTypeManager implements IndividualLoanTypeService {
    private final IndividualLoanTypeRepository repository;
    private final IndividualLoanTypeMapper mapper;
    private final LoanTypeBusinessRules rules;

    @Override
    public PageResponse<IndividualLoanTypeResponse> getAll(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        
        Page<IndividualLoanType> loanTypePage = repository.findAll(pageRequest);
        
        return new PageResponse<>(
            loanTypePage.getContent().stream()
                .map(mapper::toResponse)
                .toList(),
            loanTypePage.getNumber(),
            loanTypePage.getSize(),
            loanTypePage.getTotalElements(),
            loanTypePage.getTotalPages(),
            loanTypePage.isFirst(),
            loanTypePage.isLast(),
            loanTypePage.hasNext(),
            loanTypePage.hasPrevious()
        );
    }

    @Override
    public IndividualLoanTypeResponse getById(Long id) {
        return mapper.toResponse(
            repository.findById(id)
                .orElseThrow(() -> new BusinessException("Individual loan type not found: " + id))
        );
    }

    @Override
    @Transactional
    public IndividualLoanTypeResponse create(CreateIndividualLoanTypeRequest request) {
        rules.checkIfNameExists(request.getName());
        rules.checkIfAmountRangeIsValid(request.getMinAmount(), request.getMaxAmount());
        rules.checkIfTermRangeIsValid(request.getMinTermMonths(), request.getMaxTermMonths());
        
        IndividualLoanType loanType = mapper.toEntity(request);
        return mapper.toResponse(repository.save(loanType));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Individual loan type not found: " + id);
        }
        repository.deleteById(id);
    }
} 