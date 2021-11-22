package com.example.news.buzz.dao;

import com.example.news.buzz.model.NewsThread;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadDao extends MongoRepository<NewsThread, Integer> {

    NewsThread findNewsThreadById(Integer id);

    NewsThread findNewsThreadByUniqueId(String uniqueId);

}
