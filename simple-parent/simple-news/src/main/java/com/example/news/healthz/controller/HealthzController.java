package com.example.news.healthz.controller;


import com.example.news.dto.response.BasicOkResponse;
import com.example.news.exception.CodeEnum;
import com.example.news.healthz.dto.response.HealthzResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/healthz")
public class HealthzController {


    @GetMapping
    @ResponseBody
    public HealthzResponse health() {
        HealthzResponse healthzResponse = new HealthzResponse();

        healthzResponse.setData(new HealthzResponse.Data(HealthzResponse.Status.UP));

        return healthzResponse;
    }

}
