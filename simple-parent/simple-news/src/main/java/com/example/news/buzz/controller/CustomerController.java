package com.example.news.buzz.controller;

import com.example.news.buzz.dto.Customer;
import com.example.news.buzz.dto.Order;
import com.example.news.buzz.service.impl.CustomerService;
import com.example.news.buzz.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import java.util.List;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return customerService.getCustomerDetail(customerId);
    }

    @GetMapping(value = "/{customerId}/orders", produces = { "application/hal+json" })
    public CollectionModel<Order> getOrdersForCustomer(@PathVariable final String customerId) {
        List<Order> orders = orderService.getAllOrdersForCustomer(customerId);
        for (final Order order : orders) {
            Link selfLink = linkTo(methodOn(CustomerController.class)
                    .getOrderById(customerId, order.getOrderId())).withSelfRel();
            order.add(selfLink);
        }

        Link link = linkTo(methodOn(CustomerController.class)
                .getOrdersForCustomer(customerId)).withSelfRel();
        CollectionModel<Order> result = CollectionModel.of(orders, link);
        return result;
    }

    @GetMapping(value = "/{customerId}/orders/{orderId}", produces = { "application/hal+json" })
    public CollectionModel<Order> getOrderById(String customerId, String orderId) {
        //TODO:
        return null;
    }

    @GetMapping(value = "/all", produces = { "application/hal+json" })
    public CollectionModel<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerService.allCustomers();

        for (Customer customer : allCustomers) {
            String customerId = customer.getCustomerId();
            Link selfLink = linkTo(CustomerController.class).slash(customerId).withSelfRel();
            customer.add(selfLink);
            if (orderService.getAllOrdersForCustomer(customerId).size() > 0) {
                Link ordersLink = linkTo(methodOn(CustomerController.class)
                        .getOrdersForCustomer(customerId)).withRel("allOrders");
                customer.add(ordersLink);
            }
        }

        Link link = linkTo(CustomerController.class).withSelfRel();
        CollectionModel<Customer> result = CollectionModel.of(allCustomers, link);
        return result;
    }

}
