package com.example.news;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import de.bwaldvogel.mongo.backend.memory.MemoryDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@Slf4j
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
