package com.example.news.buzz.service.impl;

import com.example.news.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest extends BaseSpringTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void getCustomerDetailTest() {
        String str = "";
        //customerService.getCustomerDetail(str);
    }

}