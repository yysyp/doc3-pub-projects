package com.example.news.job.service;

import com.example.news.buzz.dao.ThreadDao;
import com.example.news.buzz.model.NewsThread;
import lombok.extern.slf4j.Slf4j;
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
public class StatusFlowJob {

    @Value("${scheduling.pageSize:2}")
    private int pageSize;

    @Value("${scheduling.maxProcessPages:10}")
    private int maxProcessPages;

    @Autowired
    private ThreadDao threadDao;

    @Autowired
    private PageProcessorTemplate<NewsThread> pageProcessorTemplate;

    public static final String[] STATUS_LIST = {"CREATED", "STAGE1ED", "STAGE2ED", "STAGE3ED", "STAGE4ED", "STAGE5ED"};
    public static final String[] STATUS_ERROR_LIST = {"STAGE1_ERR", "STAGE2_ERR", "STAGE3_ERR", "STAGE4_ERR", "STAGE5_ERR"};

    @Scheduled(cron  = "${scheduling.statusflow.cron:*/5 * * * * ?}")
    @SchedulerLock(name = "updateStatus", lockAtLeastFor = "PT1S", lockAtMostFor = "PT10M")
    public void updateStatus() {
        log.info("News status check routine started...");
        updateStatus(STATUS_LIST[0], STATUS_LIST[1], STATUS_ERROR_LIST[0], newsThread -> {
            // Call xxx to check consistence...
            return true;
        });
//        for (int i = 0; i < STATUS_LIST.length - 1; i++) {
//            updateStatus(STATUS_LIST[i], STATUS_LIST[i+1], STATUS_ERROR_LIST[i], newsThread -> {
//                switch (STATUS_LIST[i]) {
//                    case "": xxx
//                }
//                return false;
//            });
//        }
        log.info("News status check routine ended.");
    }


    private void updateStatus(String statusFrom, String statusTo, String statusErr, UpdateStatusHandler<NewsThread> updateStatusCallBack) {
        PageProcessorTemplate.ProcessorParam processorParam = new PageProcessorTemplate.ProcessorParam();
        processorParam.setPageSize(pageSize);
        processorParam.setMaxProcessPages(maxProcessPages);
        LocalDateTime now = LocalDateTime.now();
        pageProcessorTemplate.processByPage(processorParam, new PageProcessorTemplate.PageCallback<NewsThread>() {
            @Override
            public List fetchPageData(Pageable pageable) {
                log.info("News processor fetch one page...");
                List<NewsThread> newsThreadList =
                        threadDao.findNewsThreadByStatus(statusFrom, pageable);
                return newsThreadList;
            }

            @Override
            public void processPage(List<NewsThread> list) {
                log.info("News processing list...");
                for (NewsThread newsThread : list) {
                    log.info("News processing news={}", newsThread);
                    boolean processResult = false;
                    try {
                        processResult = updateStatusCallBack.process(newsThread);
                    } catch (Exception err) {
                        log.error("News processing error, NewsThread={}, err={}", newsThread, err);
                    }
                    if (processResult) {
                        newsThread.setStatus(statusTo);
                    } else {
                        newsThread.setStatus(statusErr);
                    }
                }
                threadDao.saveAll(list);
            }
        });
    }

    public interface UpdateStatusHandler<T> {
        boolean process(T t);
    }

}
