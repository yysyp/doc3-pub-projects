package com.example.news.buzz.dao;

import com.example.news.buzz.model.NewsThread;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ThreadDao extends MongoRepository<NewsThread, Integer> {

    NewsThread findNewsThreadById(Integer id);

    NewsThread findNewsThreadByUniqueId(String uniqueId);

    List<NewsThread> findNewsThreadByUserAndCreatedAtLessThanEqual(
            @Param("user") String user,
            @Param("createdAt") LocalDateTime localDateTime,
            Pageable pageable
            );

// Difference between above one is that the above one will only query one page date
// And NOT to count total records.
// But this one returns Pageable object, it actually fired two query, one is for one
// page records, another query is count the total records.
//    Pageable<NewsThread> findNewsThreadByUserEqualAndCreatedAtLessThanEqual(
//            @Param("user") String user,
//            @Param("createdAt") LocalDateTime localDateTime,
//            Pageable pageable
//    );

}
