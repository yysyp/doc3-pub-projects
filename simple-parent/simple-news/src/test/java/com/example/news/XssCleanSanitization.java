package com.example.news;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class XssCleanSanitization {

    public static void main(String[] args) {

        String unsafe = "<script>alert('hi xss');</script>";
        String safeString = Jsoup.clean(unsafe, Safelist.basic());
        System.out.println("--->>1: " + safeString);

        String s2 = StringEscapeUtils.escapeHtml4(unsafe);
        System.out.println("--->>2: "+s2);
        System.out.println("--->>3: "+ StringEscapeUtils.unescapeHtml4(s2));

    }
}
