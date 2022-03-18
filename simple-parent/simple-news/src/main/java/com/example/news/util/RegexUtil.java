package com.example.news.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static String findByRegex(String content, String regex, int groupI) {
        if (groupI < 0) {
            groupI = 0;
        }
        Pattern pt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher mt = pt.matcher(content);
        while (mt.find()) {
            String matchStr = mt.group(groupI);
            return matchStr;
        }
        return "";
    }

}
