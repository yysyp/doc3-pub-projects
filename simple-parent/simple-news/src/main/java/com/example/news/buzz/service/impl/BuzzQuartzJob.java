package com.example.news.buzz.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class BuzzQuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            log.info("--->>Quartz job executing...");
            ApplicationContext ctx = getContext(jobExecutionContext);
            log.info("Bean get from ctx = {}", ctx.getBean("threadService"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ApplicationContext getContext(JobExecutionContext context) throws SchedulerException {
        ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
        if (ctx == null) {
            throw new JobExecutionException("No application context available in the scheduler context");
        }
        return ctx;
    }

}
