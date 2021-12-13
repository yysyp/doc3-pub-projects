package com.example.news;

import com.example.news.buzz.model.NewsThread;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import de.bwaldvogel.mongo.backend.memory.MemoryDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;

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
        prepareTestData();

        log.info("--->> Mongodb mock started");
        SpringApplication.run(NewsApplication.class, args);
    }

    private static void prepareTestData() {
        log.info("News mongodb data preparing data...");
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/newsdb?retryWrites=false");
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "newsdb");

        LocalDateTime createAt = LocalDateTime.now();
        for (int i = 0; i < 11; i++) {
            NewsThread newsThread = new NewsThread();
            newsThread.setId(i);
            newsThread.setUniqueId(i+"");
            newsThread.setUser("string");
            newsThread.setStatus("CREATED");
            newsThread.setCreatedAt(createAt);
            mongoTemplate.save(newsThread);
        }
        log.info("News mongodb data prepared.");
    }

    @PreDestroy
    public void onExit() {
        log.info("--->> Stopping mongodb mock...");
        mongoServer.shutdownNow();
    }

}
