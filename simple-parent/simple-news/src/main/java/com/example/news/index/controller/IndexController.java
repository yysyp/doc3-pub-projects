package com.example.news.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping("/")
    public String indexTodocs() {
        return "/springdoc/docs.html";
    }

    @GetMapping("/db")
    public String db() {
        return "/h2-console?url=jdbc:h2:mem:testdb";
    }

}
