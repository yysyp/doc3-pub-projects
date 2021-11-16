package com.example.news.buzz.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@Builder
@Data
public class Order extends RepresentationModel<Order> {
    private String orderId;
    private double price;
    private int quantity;

    // standard getters and setters
}