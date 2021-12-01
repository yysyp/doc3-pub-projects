package com.example.news.buzz.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class BuzzScheduleJob {

    @Scheduled(cron = "*/1 * * * * ?")
    @SchedulerLock(name = "buzz.scheduledRun",
    lockAtLeastFor = "PT1S",
    lockAtMostFor = "PT10M")
    public void scheduledRun() {
        log.info("SN buzz schedule job started "
                +LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        LockAssert.assertLocked();

        long millionSecons = RandomUtils.nextLong(3000, 9000);
        log.info("SN buzz schedule task going to sleep for millionSeconds={}", millionSecons);
        try {
            Thread.sleep(millionSecons);
        } catch (InterruptedException e) {
            log.info("Thread sleep interrupted, e={}", e.getMessage());
        }

        log.info("SN buzz schedule job ended."+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Scheduled(cron = "*/2 * * * * ?")
    @SchedulerLock(name = "buzz.scheduledRun",
            lockAtMostFor = "PT1M")
    public void scheduledRun2() {
        log.info("SN buzz schedule job2 started "+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        long millionSecons = RandomUtils.nextLong(100, 9000);
        log.info("SN buzz schedule task2 going to sleep for millionSeconds={}", millionSecons);
        try {
            Thread.sleep(millionSecons);
        } catch (InterruptedException e) {
            log.info("Thread sleep interrupted, e={}", e.getMessage());
        }

        log.info("SN buzz schedule job2 ended." + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

//    @Scheduled(cron = "*/1 * * * * ?")
//    @SchedulerLock(name = "buzz.scheduledRun",
//            lockAtMostFor = "PT1M")
//    public void scheduledRun3() {
//        log.info("SN buzz schedule job3 started " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//        long millionSecons = RandomUtils.nextLong(10, 9000);
//        log.info("SN buzz schedule task3 going to sleep for millionSeconds={}", millionSecons);
//        try {
//            Thread.sleep(millionSecons);
//        } catch (InterruptedException e) {
//            log.info("Thread sleep interrupted, e={}", e.getMessage());
//        }
//
//        log.info("SN buzz schedule job3 ended."+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//    }
//
//    @Scheduled(cron = "0/1 * * * * ?")
//    @SchedulerLock(name = "buzz.scheduledRun4",
//            lockAtMostFor = "PT1M")
//    public void scheduledRun4() {
//        log.info("SN buzz schedule job4 started"+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//        long millionSecons = RandomUtils.nextLong(10, 9000);
//        log.info("SN buzz schedule task4 going to sleep for millionSeconds={}", millionSecons);
//        try {
//            Thread.sleep(millionSecons);
//        } catch (InterruptedException e) {
//            log.info("Thread sleep interrupted, e={}", e.getMessage());
//        }
//
//        log.info("SN buzz schedule job4 ended."+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//    }
//
//    @Scheduled(cron = "*/1 * * * * ?")
//    public void scheduledRun30() {
//        log.info("SN buzz schedule 30 running."+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//    }
//    @Scheduled(cron = "*/1 * * * * ?")
//    public void scheduledRun31() {
//        log.info("SN buzz schedule 31 running."+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//    }
//    @Scheduled(cron = "*/1 * * * * ?")
//    public void scheduledRun32() {
//        log.info("SN buzz schedule 32 running."+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//    }
//    @Scheduled(cron = "*/1 * * * * ?")
//    public void scheduledRun33() {
//        log.info("SN buzz schedule 33 running."+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//    }


}
