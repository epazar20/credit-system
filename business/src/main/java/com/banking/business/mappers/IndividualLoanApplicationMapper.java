package com.banking.business.mappers;

import com.banking.business.dtos.requests.CreateIndividualLoanApplicationRequest;
import com.banking.business.dtos.responses.IndividualLoanApplicationResponse;
import com.banking.entities.IndividualCustomer;
import com.banking.entities.IndividualLoanApplication;
import com.banking.entities.IndividualLoanType;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;

import jakarta.annotation.Generated;

@Generated("org.mapstruct.ap.MappingProcessor")
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IndividualLoanApplicationMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "loanType", source = "loanType")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "decisionDate", ignore = true)
    @Mapping(target = "rejectionReason", ignore = true)
    @Mapping(target = "interestRate", ignore = true)
    @Mapping(target = "monthlyPayment", ignore = true)
    @Mapping(target = "totalPayment", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    IndividualLoanApplication toEntity(CreateIndividualLoanApplicationRequest request, 
                                     IndividualCustomer customer,
                                     IndividualLoanType loanType);
    
    @Mapping(target = "loanTypeId", source = "loanType.id")
    @Mapping(target = "loanTypeName", source = "loanType.name")
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerFullName", expression = "java(entity.getCustomer().getFirstName() + \" \" + entity.getCustomer().getLastName())")
    @Mapping(target = "debtToIncomeRatio", expression = "java(calculateDebtToIncomeRatio(entity))")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "requestedAmount", source = "requestedAmount")
    @Mapping(target = "termMonths", source = "termMonths")
    @Mapping(target = "interestRate", source = "interestRate")
    @Mapping(target = "monthlyPayment", source = "monthlyPayment")
    @Mapping(target = "totalPayment", source = "totalPayment")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "decisionDate", source = "decisionDate")
    @Mapping(target = "rejectionReason", source = "rejectionReason")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "updatedDate", source = "updatedDate")
    IndividualLoanApplicationResponse toResponse(IndividualLoanApplication entity);
    
    default Double calculateDebtToIncomeRatio(IndividualLoanApplication entity) {
        double totalMonthlyDebt = (entity.getTotalExistingLoanPayments() != null ? 
            entity.getTotalExistingLoanPayments() : 0.0);
        if (entity.getMonthlyPayment() != null) {
            totalMonthlyDebt += entity.getMonthlyPayment();
        }
        return totalMonthlyDebt / entity.getMonthlyIncome();
    }
} 