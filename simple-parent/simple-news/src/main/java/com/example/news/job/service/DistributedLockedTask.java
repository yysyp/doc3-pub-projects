package com.example.news.job.service;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@Slf4j
@Component
public class DistributedLockedTask {

    @Scheduled(cron = "${scheduling.distributedlock.cron}")
    @SchedulerLock(name = "distributedLockTask1", lockAtLeastFor = "PT1S", lockAtMostFor = "PT3M")
    public void distributedLockTask1() throws InterruptedException {
        log.info("--->>DistributedLockTask1 run...");

        Thread.sleep(4000);
    }

}
