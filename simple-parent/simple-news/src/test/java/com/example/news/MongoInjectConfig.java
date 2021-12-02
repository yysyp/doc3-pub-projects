package com.example.news;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.bwaldvogel.mongo.MongoServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.NoRepositoryBean;

import java.lang.reflect.Member;

@Slf4j
@TestConfiguration
public class MongoInjectConfig implements InitializingBean, DisposableBean {

    @Bean(name = "mongoTemplate")
    public MongoTemplate getMongoTemplate(@Autowired MongoClient mongoClient) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "newsdb");
        return mongoTemplate;
    }

    @Bean
    public MongoClient getMongoClient() {
        MongoServer server = MemoryMongoHolder.getInstance().getMongoServer();
        log.info("--->>MongoServer server={}", server);
        return MongoClients.create("mongodb://localhost:27777/newsdb?retryWrites=false");
    }


    @Override
    public void destroy() throws Exception {
        log.info("--->>destroy...");
        try {
            MemoryMongoHolder.getInstance().shutdownMongo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("--->>started...");

    }
}
