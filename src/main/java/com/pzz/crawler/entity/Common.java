package com.pzz.crawler.entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Common {
    public static Document document;

    static {
        try {
            document = Jsoup.connect("http://www.jueshitangmen.info/3048/3598.html").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
