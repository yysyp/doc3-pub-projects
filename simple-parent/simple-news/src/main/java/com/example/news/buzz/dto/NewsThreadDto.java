package com.example.news.buzz.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsThreadDto {

    private Integer id;

    private String uniqueId;

    private String user;
}
