package com.pzz.crawler.dl;

import com.pzz.crawler.entity.Chapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class ParserThread implements Runnable {
    private List<Chapter> chapters;

    public ParserThread(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public static void chapterParser(Chapter chapter) throws IOException {
        Document document = Jsoup.connect(chapter.getURL()).get();
        String title = document.getElementsByClass("bookname").get(0).getElementsByTag("h1").text();
        Elements elements = document.getElementById("content").getElementsByTag("p");
        StringBuilder content = new StringBuilder();
        for (Element e : elements) {
            content.append("\t").append(e.text()).append("\n");
        }
        System.out.println(title);
        chapter.setTitle(title);
        chapter.setContent(content.toString());
    }

    public void run() {
        int i = 0;
        while (true) {
            synchronized (this) {
                for (; i < chapters.size(); i++) {
                    if (chapters.get(i).getTitle() == null)
                        break;
                }
                try {
                    chapterParser(chapters.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                if (i == chapters.size() - 1)
                    return;
            }
        }
    }
}
