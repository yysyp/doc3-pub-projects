package com.example.news.buzz.dto.response;

import com.example.news.buzz.dto.Person;
import com.example.news.dto.response.BasicOkResponse;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse extends BasicOkResponse {

    Person data;


}
