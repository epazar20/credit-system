package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "corporate_customers")
@PrimaryKeyJoinColumn(name = "customer_id")
public class CorporateCustomer extends Customer {
    
    @Column(name = "company_name", nullable = false)
    private String companyName;
    
    @Column(name = "tax_number", nullable = false, unique = true)
    private String taxNumber;
    
    @Column(name = "contact_person_name", nullable = false)
    private String contactPersonName;
    
    @Column(name = "contact_person_title")
    private String contactPersonTitle;
} 