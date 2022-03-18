package com.example.news.job.service;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@Slf4j
@Component
public class DistributedLockedTask {

    @Scheduled(cron = "${scheduling.distributedlock.cron}")
    @SchedulerLock(name = "distributedLockTask1")
    public void distributedLockTask1() throws InterruptedException {
        MDC.put("TRACE_ID", ""+RandomUtils.nextLong(100000000L, 999999999L));
        log.info("--->>distributedLockTask1 running...");
        try {
            LockAssert.assertLocked();
        } catch (IllegalStateException e) {
            log.error("--->>The task is not locked.");
            return;
        }
        long randomSleep = RandomUtils.nextLong(0, 1000);
        Thread.sleep(randomSleep);
        //log.debug("--->>distributedLockTask1 stopped after sleep {}ms", randomSleep);
        MDC.clear();
    }

}
