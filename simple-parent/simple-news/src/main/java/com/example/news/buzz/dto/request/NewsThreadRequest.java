package com.example.news.buzz.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsThreadRequest {

    private Integer id;

    private String uniqueId;

    private String user;

    private BigDecimal rate;

    private Boolean okFlag;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;


}
