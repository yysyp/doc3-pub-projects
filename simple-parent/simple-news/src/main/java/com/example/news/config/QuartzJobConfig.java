package com.example.news.config;


import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class QuartzJobConfig {

    private final static String SYNCDATAJOB = "SYNCDATAJOB";
    private final static String SYNCDATAGROUP = "SYNCDATAGROUP";
    private final static String SYNCDATATRIGGER = "SYNCDATATRIGGER";


    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    private void init() throws Exception {
        scheduler.addJob(syncData(), true, true);
        if (!scheduler.checkExists(new TriggerKey(SYNCDATATRIGGER, SYNCDATAGROUP))) {
            scheduler.scheduleJob(triggerSyncData());
        }
    }

    private JobDetail syncData() {
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setKey(new JobKey(SYNCDATAJOB, SYNCDATAGROUP));
        jobDetail.setJobClass(QuartzJobBean.class);
        return jobDetail;
    }

    private Trigger triggerSyncData() {

        return null;
    }


}
