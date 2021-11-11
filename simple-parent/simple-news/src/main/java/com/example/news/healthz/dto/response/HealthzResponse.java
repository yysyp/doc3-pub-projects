package com.example.news.healthz.dto.response;

import com.example.news.dto.response.BasicOkResponse;
import lombok.*;

import java.sql.Statement;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HealthzResponse extends BasicOkResponse {

    Data data;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class Data {
        Status status;
    }

    public static enum Status {
        UP, DOWN;
    }

}
