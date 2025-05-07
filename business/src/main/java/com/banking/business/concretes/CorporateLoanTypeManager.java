package com.banking.business.concretes;

import com.banking.business.abstracts.CorporateLoanTypeService;
import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.requests.CreateCorporateLoanTypeRequest;
import com.banking.business.dtos.responses.CorporateLoanTypeResponse;
import com.banking.business.mappers.CorporateLoanTypeMapper;
import com.banking.business.rules.LoanTypeBusinessRules;
import com.banking.core.exceptions.BusinessException;
import com.banking.entities.CorporateLoanType;
import com.banking.repositories.abstracts.CorporateLoanTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CorporateLoanTypeManager implements CorporateLoanTypeService {
    private final CorporateLoanTypeRepository repository;
    private final CorporateLoanTypeMapper mapper;
    private final LoanTypeBusinessRules rules;

    @Override
    public PageResponse<CorporateLoanTypeResponse> getAll(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        
        Page<CorporateLoanType> loanTypePage = repository.findAll(pageRequest);
        
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
    public CorporateLoanTypeResponse getById(Long id) {
        return mapper.toResponse(
            repository.findById(id)
                .orElseThrow(() -> new BusinessException("Corporate loan type not found: " + id))
        );
    }

    @Override
    @Transactional
    public CorporateLoanTypeResponse create(CreateCorporateLoanTypeRequest request) {
        rules.checkIfNameExists(request.getName());
        rules.checkIfAmountRangeIsValid(request.getMinAmount(), request.getMaxAmount());
        rules.checkIfTermRangeIsValid(request.getMinTermMonths(), request.getMaxTermMonths());
        
        CorporateLoanType loanType = mapper.toEntity(request);
        return mapper.toResponse(repository.save(loanType));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Corporate loan type not found: " + id);
        }
        repository.deleteById(id);
    }
} 