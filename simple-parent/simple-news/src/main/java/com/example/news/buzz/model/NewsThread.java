package com.example.news.buzz.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Document(collation = "thread")
@Document(collection = "thread")
public class NewsThread implements java.io.Serializable {

    @Id
    private Integer id;

    @Indexed(unique = true)
    private String uniqueId;

    private String user;

}
