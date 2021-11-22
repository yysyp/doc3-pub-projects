package com.example.news.buzz.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsThreadRequest {

    private Integer id;

    private String uniqueId;

    private String user;

}
