package com.banking.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualCustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String nationalId;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
} 