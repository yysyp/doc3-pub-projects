package com.example.news.util;

import java.time.LocalDateTime;

public class DateTimeUtil {

    public static String getTimestamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.toString();
    }

}
