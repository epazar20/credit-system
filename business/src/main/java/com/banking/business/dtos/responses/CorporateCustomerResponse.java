package com.banking.business.dtos.responses;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorporateCustomerResponse {
    private Long id;
    private String companyName;
    private String taxNumber;
    private String contactPersonName;
    private String contactPersonTitle;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
} 