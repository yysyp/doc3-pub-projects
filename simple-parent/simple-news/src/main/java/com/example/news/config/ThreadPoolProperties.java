package com.example.news.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties("threadpool")
public class ThreadPoolProperties {

    private int corePoolSize;

    private int maxPoolSize;

    private int queueSize;

    private int schedulerPoolSize;

}
