package com.example.news.dto.response;

import com.example.news.exception.CodeEnum;
import com.example.news.util.DateTimeUtil;
import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse implements Serializable {

    String code;
    String message;
    String trace;
    String correlationId;
    String instance;
    String timestamp;
    String path;

}
