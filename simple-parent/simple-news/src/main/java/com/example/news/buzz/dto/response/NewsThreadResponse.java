package com.example.news.buzz.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsThreadResponse {

    private Integer id;

    private String uniqueId;

    private String user;

}
