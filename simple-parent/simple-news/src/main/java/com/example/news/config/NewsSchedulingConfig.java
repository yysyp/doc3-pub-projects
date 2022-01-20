package com.example.news.config;

import com.example.news.job.service.NewsTask;
import com.example.news.job.service.NewsTrigger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Slf4j
@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class NewsSchedulingConfig implements SchedulingConfigurer {

    @Autowired
    private SchedulingProperties schedulingProperties;

    @Autowired
    private NewsTask newsTask;

    @Autowired
    private NewsTrigger newsTrigger;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(schedulingProperties.getPoolSize());
        scheduler.setThreadNamePrefix(schedulingProperties.getPrefix());
        scheduler.setRemoveOnCancelPolicy(true);
        scheduler.initialize();
        taskRegistrar.setScheduler(scheduler);
        //taskRegistrar.addTriggerTask(newsTask, newsTrigger);
    }

}
