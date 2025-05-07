package com.banking.business.concretes;

import com.banking.business.abstracts.CustomerService;
import com.banking.business.dtos.common.PageResponse;
import com.banking.business.dtos.responses.CorporateCustomerResponse;
import com.banking.business.dtos.responses.IndividualCustomerResponse;
import com.banking.entities.CorporateCustomer;
import com.banking.entities.Customer;
import com.banking.entities.IndividualCustomer;
import com.banking.repositories.abstracts.CorporateCustomerRepository;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService<Customer> {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;

    @Override
    public List<Customer> getAll() {
        List<Customer> individuals = individualCustomerRepository.findAll().stream()
            .map(customer -> (Customer) customer)
            .collect(Collectors.toList());
        List<Customer> corporates = corporateCustomerRepository.findAll().stream()
            .map(customer -> (Customer) customer)
            .collect(Collectors.toList());
        individuals.addAll(corporates);
        return individuals;
    }

    @Override
    public Customer getById(Long id) {
        Customer individual = individualCustomerRepository.findById(id).orElse(null);
        if (individual != null) return individual;
        return corporateCustomerRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        if (individualCustomerRepository.existsById(id)) {
            individualCustomerRepository.deleteById(id);
        } else if (corporateCustomerRepository.existsById(id)) {
            corporateCustomerRepository.deleteById(id);
        }
    }

    @Override
    public PageResponse<IndividualCustomerResponse> getAllIndividualCustomers(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        
        Page<IndividualCustomer> individualCustomerPage = individualCustomerRepository.findAll(pageRequest);
        
        return new PageResponse<>(
            individualCustomerPage.getContent().stream()
                .map(customer -> new IndividualCustomerResponse(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getNationalId(),
                    customer.getBirthDate(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getAddress(),
                    customer.getCreatedDate(),
                    customer.getUpdatedDate()
                ))
                .collect(Collectors.toList()),
            individualCustomerPage.getNumber(),
            individualCustomerPage.getSize(),
            individualCustomerPage.getTotalElements(),
            individualCustomerPage.getTotalPages(),
            individualCustomerPage.isFirst(),
            individualCustomerPage.isLast(),
            individualCustomerPage.hasNext(),
            individualCustomerPage.hasPrevious()
        );
    }

    @Override
    public PageResponse<CorporateCustomerResponse> getAllCorporateCustomers(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        
        Page<CorporateCustomer> corporateCustomerPage = corporateCustomerRepository.findAll(pageRequest);
        
        return new PageResponse<>(
            corporateCustomerPage.getContent().stream()
                .map(customer -> new CorporateCustomerResponse(
                    customer.getId(),
                    customer.getCompanyName(),
                    customer.getTaxNumber(),
                    customer.getContactPersonName(),
                    customer.getContactPersonTitle(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getAddress(),
                    customer.getCreatedDate(),
                    customer.getUpdatedDate()
                ))
                .collect(Collectors.toList()),
            corporateCustomerPage.getNumber(),
            corporateCustomerPage.getSize(),
            corporateCustomerPage.getTotalElements(),
            corporateCustomerPage.getTotalPages(),
            corporateCustomerPage.isFirst(),
            corporateCustomerPage.isLast(),
            corporateCustomerPage.hasNext(),
            corporateCustomerPage.hasPrevious()
        );
    }
} 