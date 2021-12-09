package com.example.news.job.service;

import com.example.news.buzz.dao.ThreadDao;
import com.example.news.buzz.model.NewsThread;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class NewThreadJob {

    @Value("${scheduling.pageSize:2}")
    private int pageSize;

    @Value("${scheduling.maxProcessPages:10}")
    private int maxProcessPages;

    @Autowired
    private ThreadDao threadDao;

    @Autowired
    private PageProcessorTemplate<NewsThread> pageProcessorTemplate;

    @Scheduled(cron  = "${scheduling.houseKeeper.cron:*/3 * * * * ?}")
    @SchedulerLock(name = "threadHouseKeeperRoutine", lockAtLeastFor = "PT1S", lockAtMostFor = "PT10M")
    public void threadHouseKeeperRoutine() {
        log.info("News house keeper routine started...");
        //Check clock time.
        //Check locker.
//        try {
//            LockAssert.assertLocked();
//        } catch (IllegalStateException illegalStateException) {
//            log.info("News house keeper exit as it has lock conflicts.");
//            return;
//        }

        PageProcessorTemplate.ProcessorParam processorParam = new PageProcessorTemplate.ProcessorParam();
        processorParam.setPageSize(pageSize);
        processorParam.setMaxProcessPages(maxProcessPages);
        String user = "string";
        LocalDateTime now = LocalDateTime.now();
        pageProcessorTemplate.processByPage(processorParam, new PageProcessorTemplate.PageCallback<NewsThread>() {
            @Override
            public List fetchPageData(Pageable pageable) {
                log.info("News processor fetch one page...");
                List<NewsThread> newsThreadList =
                        threadDao.findNewsThreadByUserAndCreatedAtLessThanEqual(user, now, pageable);

                return newsThreadList;
            }

            @Override
            public void processPage(List<NewsThread> list) {
                log.info("News processing list...");
                for (NewsThread newsThread : list) {
                    log.info("News processing news={}", newsThread);
                    newsThread.setUser("system");
                }
                threadDao.saveAll(list);
            }
        });

        log.info("News house keeper routine ended.");
    }

}
