package com.example.news.dto.response;

import com.example.news.exception.CodeEnum;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BasicOkResponse implements Serializable {

    String code = CodeEnum.SUCCESS.getCode();

//    Map<String, ResLink> links;
//
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Getter
//    @Setter
//    @ToString
//    static class ResLink implements Serializable {
//        String href;
//        String title;
//    }

}
