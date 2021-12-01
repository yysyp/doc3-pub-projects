package com.example.news;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import de.bwaldvogel.mongo.backend.memory.MemoryDatabase;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PreDestroy;


@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30M")
@Slf4j
@EnableRetry
@SpringBootApplication
public class NewsApplication {

    static MongoServer mongoServer;

    public static void main(String[] args) {
        log.info("--->> Starting Mongodb mock....");
        MemoryBackend memoryBackend = new MemoryBackend();
        MemoryDatabase memoryDatabase = memoryBackend.openOrCreateDatabase("newsdb");
        mongoServer = new MongoServer(memoryBackend);
        mongoServer.bind("localhost", 27017);
        log.info("--->> Mongodb mock started");
        SpringApplication.run(NewsApplication.class, args);
    }

    @PreDestroy
    public void onExit() {
        log.info("--->> Stopping mongodb mock...");
        mongoServer.shutdownNow();
    }

}
