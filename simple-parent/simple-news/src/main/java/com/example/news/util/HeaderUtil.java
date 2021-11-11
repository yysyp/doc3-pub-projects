package com.example.news.util;

import org.apache.commons.lang3.StringUtils;

public class HeaderUtil {

    public static boolean isTrace() {
        String trace = RequestContextUtil.getRequest().getHeader("trace");
        return StringUtils.isNotBlank(trace);
    }
}
