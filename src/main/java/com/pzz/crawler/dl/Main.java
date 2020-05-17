package com.pzz.crawler.dl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> category = new ArrayList<>();
        Document document = Jsoup.connect(Constant.CategoryURL).get();
        Elements dd = document.getElementsByTag("dd");
        dd.subList(0, 12).clear();

        Elements hrefs = dd.select("a[href]");
        for (int i = 0; i < hrefs.size(); i++) {
            String href = hrefs.get(i).attr("href");
            category.add(i, href);
        }

        System.out.println(category);
    }
}
