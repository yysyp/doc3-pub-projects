package yp.song;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

/**
 * Test dynamically modify the cron expression
 */
@Configuration
@EnableScheduling
public class SpringSchedulingConfigurerBeanExample {

    private AtomicInteger counter = new AtomicInteger(0);

    @Scheduled(fixedRate = 1000)
    public void fixedRateJob() {
        int jobId = counter.incrementAndGet();
        System.out.println("Seconds count, by jobId: " + jobId);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringSchedulingConfigurerBeanExample.class);

        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }


    @Configuration
    static class RegisterTaskSchedulerViaSchedulingConfigurer implements SchedulingConfigurer {

        protected String cron = "*/1 * * * * ?";

        public RegisterTaskSchedulerViaSchedulingConfigurer() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    cron = "0/10 * * * * ?";
                    System.err.println("cron change to: " + cron);
                }
            }).start();

        }

        @Override
        public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
            String oldCron = "*/1 * * * * ?";
            taskRegistrar.setTaskScheduler(poolScheduler());

            System.out.println("--Integer.MAX_VALUE=" + Integer.MAX_VALUE);
            //for (long i = 0; i < (Integer.MAX_VALUE + 1); i++) { //1 -- 2147483647+1; If write in this way, it will be ignored
            Runnable runnableTask = () -> System.out.println("-->>Job task executed at ->" + new Date());

            Trigger trigger = new Trigger() {

                @Override
                public Date nextExecutionTime(TriggerContext triggerContext) {
//                    if (!StringUtils.equalsAnyIgnoreCase(cron, oldCron)) {
//                        taskRegistrar.setTriggerTasksList(new ArrayList<TriggerTask>());
//                        configureTasks(taskRegistrar);
//                        taskRegistrar.destroy();
//                        taskRegistrar.setScheduler(poolScheduler());
//                        taskRegistrar.afterPropertiesSet();
//                        return null;
//                    }
                    CronTrigger crontrigger = new CronTrigger(cron);
                    return crontrigger.nextExecutionTime(triggerContext);
                }
            };
            taskRegistrar.addTriggerTask(runnableTask, trigger);
            System.out.println("--IntervalTask added.");
        }

        @Bean
        public TaskScheduler poolScheduler() {
            ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
            scheduler.setThreadNamePrefix("poolScheduler-");
            scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
            scheduler.setWaitForTasksToCompleteOnShutdown(true);
            //scheduler.setAwaitTerminationSeconds(60);
            scheduler.setPoolSize(2);
            return scheduler;
        }
    }
}
