package com.example.news.job.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@Slf4j
@Component
public class FixedTimeConfigTask {

    //Can't change the interval without restart app.
    @Scheduled(fixedDelayString  = "${scheduling.fixedtask.interval}")
    public void fixedTaskRun() throws InterruptedException {
        log.info("--->>Fixed delay task run...");
        // added sleep to simulate method
        // which takes longer to execute.
        Thread.sleep(4000);
    }

    //Can't change the interval without restart app.
    @Scheduled(cron = "${scheduling.fixedtask.cron}")
    public void intervalInCronRun() throws InterruptedException {
        log.info("--->>Interval run in cron...");
    }

}
