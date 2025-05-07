package com.banking.business.mappers;

import com.banking.business.dtos.requests.CreateCorporateLoanApplicationRequest;
import com.banking.business.dtos.responses.CorporateLoanApplicationResponse;
import com.banking.entities.CorporateCustomer;
import com.banking.entities.CorporateLoanApplication;
import com.banking.entities.CorporateLoanType;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CorporateLoanApplicationMapper {
    
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
    @Mapping(target = "monthlyRevenue", source = "request.monthlyRevenue")
    @Mapping(target = "deletedDate", ignore = true)
    CorporateLoanApplication toEntity(CreateCorporateLoanApplicationRequest request, 
                                    CorporateCustomer customer,
                                    CorporateLoanType loanType);
    
    @Mapping(source = "customer.companyName", target = "customerCompanyName")
    @Mapping(source = "loanType.name", target = "loanTypeName")
    @Mapping(source = "loanType.id", target = "loanTypeId")
    @Mapping(source = "requestedAmount", target = "requestedAmount")
    @Mapping(source = "termMonths", target = "termMonths")
    @Mapping(source = "interestRate", target = "interestRate")
    @Mapping(source = "monthlyPayment", target = "monthlyPayment")
    @Mapping(source = "totalPayment", target = "totalPayment")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "decisionDate", target = "decisionDate")
    @Mapping(source = "rejectionReason", target = "rejectionReason")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "updatedDate", target = "updatedDate")
    @Mapping(source = "annualRevenue", target = "annualRevenue")
    @Mapping(source = "monthlyRevenue", target = "monthlyRevenue")
    @Mapping(source = "yearsInBusiness", target = "yearsInBusiness")
    CorporateLoanApplicationResponse toResponse(CorporateLoanApplication entity);
    
    List<CorporateLoanApplicationResponse> toResponseList(List<CorporateLoanApplication> entities);
    
    default Double calculateDebtToIncomeRatio(CorporateLoanApplication entity) {
        double totalMonthlyDebt = (entity.getTotalExistingLoanPayments() != null ? 
            entity.getTotalExistingLoanPayments() : 0.0);
        if (entity.getMonthlyPayment() != null) {
            totalMonthlyDebt += entity.getMonthlyPayment();
        }
        return totalMonthlyDebt / entity.getMonthlyRevenue();
    }
} 