package com.example.news;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String [] args) {
        String msg = "My app execute step1 time cost is 352 ms and the file=533a4559-e55c-18b3-8456-555563322002" +
                "and the step2 time cost is 666 ms";

        List<String> finded = findByRegex(msg, "(\\d+) ms", 1);
        System.out.println("--->>"+finded);
        //--->>[352, 666]
    }

    public static List<String> findByRegex(String content, String regex, int group) {
        if (group < 0) {
            group = 0;
        }
        List<String> result = new ArrayList<>();
        Pattern pt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher mt = pt.matcher(content);
        while (mt.find()) {
            int gc = mt.groupCount();
            if (group <= gc) {
                result.add(mt.group(group));
            } else {
                result.add(mt.group());
            }
        }
        return result;
    }

}
