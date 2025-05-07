package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@Entity
@Table(name = "individual_customers")
@PrimaryKeyJoinColumn(name = "customer_id")
public class IndividualCustomer extends Customer {
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(name = "national_id", nullable = false, unique = true, length = 11)
    private String nationalId;
    
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    
    public int calculateAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
} 