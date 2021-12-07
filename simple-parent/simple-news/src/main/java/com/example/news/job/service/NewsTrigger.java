package com.example.news.job.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Slf4j
@Component
public class NewsTrigger implements Trigger {

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        String cron = getNewsCron();
        log.info("--->>Trigger nextExecutionTime cron={}", cron);
        if (!CronExpression.isValidExpression(cron)) {
            log.info("--->>Trigger Cron expression is invalid cron={}", cron);
            return null; // return null so the trigger will stop.
        }

        CronTrigger trigger = new CronTrigger(cron);
        Date nextExecutionTime = trigger.nextExecutionTime(triggerContext);
        return nextExecutionTime;
    }

    //Here the configuration can be dynamically loaded from database etc.
    //The cron expression can be dynamically changed during you app running.
    public String getNewsCron() {
        return "0/30 * * * * ?";
    }

}
