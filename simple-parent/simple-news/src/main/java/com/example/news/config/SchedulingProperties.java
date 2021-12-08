package com.example.news.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties("scheduling")
public class SchedulingProperties {
    private int poolSize;

    private String prefix;


}
