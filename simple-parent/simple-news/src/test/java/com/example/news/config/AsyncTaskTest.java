package com.example.news.config;

import com.example.news.BaseSpringTest;
import com.example.news.async.service.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class AsyncTaskTest extends BaseSpringTest {

    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void test1() throws Exception {
        log.info("--->>test1 ...");
        asyncTask.asyncTaskRun();
        log.info("--->>after asyncTaskRun call...");
        Thread.sleep(3000);
        log.info("--->>test1 end.");
    }

}
