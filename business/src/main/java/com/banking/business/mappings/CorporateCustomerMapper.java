package com.banking.business.mappings;

import com.banking.business.dtos.requests.CreateCorporateCustomerRequest;
import com.banking.business.dtos.requests.UpdateCorporateCustomerRequest;
import com.banking.business.dtos.responses.CorporateCustomerResponse;
import com.banking.entities.CorporateCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CorporateCustomerMapper {
    CorporateCustomer createCorporateCustomerRequestToCorporateCustomer(CreateCorporateCustomerRequest request);
    
    CorporateCustomer updateCorporateCustomerRequestToCorporateCustomer(UpdateCorporateCustomerRequest request);
    
    CorporateCustomerResponse corporateCustomerToResponse(CorporateCustomer corporateCustomer);
    
    List<CorporateCustomerResponse> corporateCustomersToResponses(List<CorporateCustomer> corporateCustomers);
} 