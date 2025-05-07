package com.banking.business.mappers;

import com.banking.business.dtos.requests.CreateIndividualLoanTypeRequest;
import com.banking.business.dtos.responses.IndividualLoanTypeResponse;
import com.banking.entities.IndividualLoanType;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IndividualLoanTypeMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    IndividualLoanType toEntity(CreateIndividualLoanTypeRequest request);
    
    IndividualLoanTypeResponse toResponse(IndividualLoanType entity);
    
    List<IndividualLoanTypeResponse> toResponseList(List<IndividualLoanType> entities);
} 