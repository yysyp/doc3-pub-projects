package com.example.news.buzz.service.impl;

import com.example.news.buzz.dto.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Service
public class CustomerService {


    public Customer getCustomerDetail(String customerId) {
        //TODO: // FIXME: 2021/11/16
        return Customer.builder().customerId("1").customerName("xiaom")
                .companyName("huawei").build();

    }

    public List<Customer> allCustomers() {
        List list = new ArrayList();
        Customer customer = Customer.builder().customerId("1").customerName("xiaom")
                .companyName("huawei").build();
        list.add(customer);
        customer = Customer.builder().customerId("2").customerName("xiaom2")
                .companyName("huawei2").build();
        list.add(customer);
        customer = Customer.builder().customerId("3").customerName("xiaom3")
                .companyName("huaweie").build();
        list.add(customer);

        return list;
    }
}
