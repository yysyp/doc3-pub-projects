package com.example.news.job.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NewsTask implements Runnable {

    @Override
    public void run() {
        log.info("--->> News task running...");
    }

}
