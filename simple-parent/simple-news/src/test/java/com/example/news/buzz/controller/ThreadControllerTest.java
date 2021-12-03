package com.example.news.buzz.controller;

import com.example.news.BaseSpringTest;
import com.example.news.buzz.dto.NewsThreadDto;
import com.example.news.buzz.service.impl.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import org.junit.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ThreadControllerTest extends BaseSpringTest {

    private final String threadUrl = "/api/thread";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ThreadService threadService;

    @Before
    public void setUp() {
        log.info("--->> setup");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void tearDown() {
        log.info("--->> tearDown");
    }

    @Test
    public void getNewsThread() throws Exception {
        NewsThreadDto newsThreadDto = new NewsThreadDto();
        Mockito.when(threadService.findNewsThreadById(1)).thenReturn(newsThreadDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(threadUrl+"/1")).andReturn();
        String responseString = mvcResult.getResponse().getContentAsString();
        Assert.assertNotNull(responseString);
    }


}