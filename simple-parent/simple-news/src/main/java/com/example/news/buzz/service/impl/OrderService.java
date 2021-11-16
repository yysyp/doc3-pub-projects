package com.example.news.buzz.service.impl;

import com.example.news.buzz.dto.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public List<Order> getAllOrdersForCustomer(String customerId) {
        List orders = new ArrayList();
        Order order = Order.builder().orderId("1").price(1.1d)
                .quantity(1).build();
        orders.add(order);
        order = Order.builder().orderId("2").price(2.1d)
                .quantity(2).build();
        orders.add(order);

        return orders;
    }

}
