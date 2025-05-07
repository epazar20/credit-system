package com.banking.business.abstracts;

import com.banking.entities.Customer;
import java.util.List;
import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.responses.CorporateCustomerResponse;
import com.banking.business.dtos.responses.IndividualCustomerResponse;

public interface CustomerService<T extends Customer> {
    List<T> getAll();
    T getById(Long id);
    void delete(Long id);
    PageResponse<IndividualCustomerResponse> getAllIndividualCustomers(int page, int size, String sortBy, String direction);
    PageResponse<CorporateCustomerResponse> getAllCorporateCustomers(int page, int size, String sortBy, String direction);
} 