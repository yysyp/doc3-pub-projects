package com.example.news.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncTask {

    @Async
    public void asyncTaskRun() {
        log.info("News Async run...");
    }

}
