package com.example.news;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import de.bwaldvogel.mongo.backend.memory.MemoryDatabase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemoryMongoHolder {

    private volatile static MemoryMongoHolder instance;

    private MongoServer mongoServer;

    private MemoryMongoHolder() {
        log.info("--->> Test starting Mongodb mock....");
        MemoryBackend memoryBackend = new MemoryBackend();
        MemoryDatabase memoryDatabase = memoryBackend.openOrCreateDatabase("newsdb");
        mongoServer = new MongoServer(memoryBackend);
        mongoServer.bind("localhost", 27777);
        log.info("--->> Test Mongodb mock started");
    }

    public MongoServer getMongoServer() {
        return mongoServer;
    }
    public void shutdownMongo() {
        this.mongoServer.shutdownNow();
    }

    public static MemoryMongoHolder getInstance() {
        if (instance == null) {
            synchronized (MemoryMongoHolder.class) {
                if (instance == null) {
                    instance = new MemoryMongoHolder();
                }
            }
        }
        return instance;
    }

}
