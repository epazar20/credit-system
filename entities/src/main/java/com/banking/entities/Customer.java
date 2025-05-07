package com.banking.entities;

import com.banking.entities.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Customer extends BaseEntity {
    @Column(unique = true)
    private String email;
    
    @Column(unique = true)
    private String phoneNumber;
    
    private String address;
} 