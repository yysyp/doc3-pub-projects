package com.example.news.buzz.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Data
public class Customer extends RepresentationModel<Customer> {
    private String customerId;
    private String customerName;
    private String companyName;

    // standard getters and setters
}
