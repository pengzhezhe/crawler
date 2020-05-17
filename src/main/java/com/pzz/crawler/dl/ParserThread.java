package com.pzz.crawler.dl;

import com.pzz.crawler.entity.Chapter;
import com.pzz.crawler.entity.Common;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class ParserThread implements Runnable {
    private List<Chapter> chapters;

    public ParserThread(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public static Chapter chapterParser(Document document) {
        String title = document.getElementsByClass("bookname").get(0).getElementsByTag("h1").text();
        Elements elements = document.getElementById("content").getElementsByTag("p");
        StringBuilder content = new StringBuilder();
        for (Element e : elements) {
            content.append("\t").append(e.text()).append("\n");
        }
        System.out.println(title);
        return new Chapter(title, content.toString());
    }

    public void run() {
        while (true) {
            synchronized (Common.document) {
                if (Common.document == null)
                    return;
                chapters.add(chapterParser(Common.document));
            }
        }
    }
}
