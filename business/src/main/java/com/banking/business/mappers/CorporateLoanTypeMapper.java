package com.banking.business.mappers;

import com.banking.business.dtos.requests.CreateCorporateLoanTypeRequest;
import com.banking.business.dtos.responses.CorporateLoanTypeResponse;
import com.banking.entities.CorporateLoanType;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CorporateLoanTypeMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    CorporateLoanType toEntity(CreateCorporateLoanTypeRequest request);
    
    CorporateLoanTypeResponse toResponse(CorporateLoanType entity);
    
    List<CorporateLoanTypeResponse> toResponseList(List<CorporateLoanType> entities);
} 