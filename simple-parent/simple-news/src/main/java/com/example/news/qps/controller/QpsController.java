package com.example.news.qps.controller;


import com.example.news.util.MyTimeUtil;
import com.example.news.util.PfTiming;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.checkerframework.framework.qual.StubFiles;
import org.joda.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/qps")
public class QpsController {

    @GetMapping("/get")
    public String qpsGet() {
        PfTiming pfTiming = PfTiming.start();

        long sleep = RandomUtils.nextLong(500, 3000);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            log.error("Interrupted ", e);
        }

        log.info("qps get response, cost={} ms", pfTiming.timeMs());
        return "{\"name\": \"qps \\r\\n test " + java.time.LocalDateTime.now() + "newline2 \\nhahah\\ttab \", \"price\": " + sleep + "}";
    }

}
