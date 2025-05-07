package com.banking.business.mappings;

import com.banking.business.dtos.requests.CreateIndividualCustomerRequest;
import com.banking.business.dtos.requests.UpdateIndividualCustomerRequest;
import com.banking.business.dtos.responses.IndividualCustomerResponse;
import com.banking.entities.IndividualCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IndividualCustomerMapper {
    IndividualCustomer createIndividualCustomerRequestToIndividualCustomer(CreateIndividualCustomerRequest request);
    
    IndividualCustomer updateIndividualCustomerRequestToIndividualCustomer(UpdateIndividualCustomerRequest request);
    
    IndividualCustomerResponse individualCustomerToResponse(IndividualCustomer individualCustomer);
    
    List<IndividualCustomerResponse> individualCustomersToResponses(List<IndividualCustomer> individualCustomers);
} 