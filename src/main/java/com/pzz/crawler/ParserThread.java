package com.pzz.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class ParserThread implements Runnable {
    private final List<Chapter> chapters;

    private int index;

    public ParserThread(List<Chapter> chapters, int index) {
        this.chapters = chapters;
        this.index = index;
    }

    private Chapter chapterParser(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Chapter chapter = new Chapter();
        String title = document.getElementsByClass("bookname").get(0).getElementsByTag("h1").text();
        Elements elements = document.getElementById("content").getElementsByTag("p");
        StringBuilder content = new StringBuilder();
        for (Element e : elements) {
            content.append("\t").append(e.text()).append("\n");
        }
        System.out.println("Parser:"+title);
        chapter.setURL(url);
        chapter.setTitle(title);
        chapter.setContent(content.toString());
        return chapter;
    }

    public void run() {
        int currentIndex;
        String currentURL;
        Chapter chapter;
        while (true) {
            synchronized (this) {
                if (index == chapters.size())
                    return;
                currentIndex = index++;
                currentURL = chapters.get(currentIndex).getURL();
            }
            try {
                chapter = chapterParser(currentURL);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            synchronized (chapters) {
                Chapter realChapter = chapters.get(currentIndex);
                realChapter.setTitle(chapter.getTitle());
                realChapter.setContent(chapter.getContent());
            }
        }
    }
}
