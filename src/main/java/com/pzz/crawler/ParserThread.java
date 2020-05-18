package com.pzz.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class ParserThread implements Runnable {
    /**
     * 章节列表
     */
    private final List<Chapter> chapters;

    /**
     * 待解析章节索引
     */
    private int index;

    public ParserThread(List<Chapter> chapters, int index) {
        this.chapters = chapters;
        this.index = index;
    }

    /**
     * 解析章节
     *
     * @param url 章节页面URL
     * @return
     * @throws IOException
     */
    private Chapter chapterParser(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Chapter chapter = new Chapter();
        String title = document.getElementsByClass("bookname").get(0).getElementsByTag("h1").text();
        Elements elements = document.getElementById("content").getElementsByTag("p");
        StringBuilder content = new StringBuilder();
        for (Element e : elements) {
            content.append("\t").append(e.text()).append("\n");
        }
        System.out.println("Parser:" + title);
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
            //获取当前待解析的章节
            synchronized (this) {
                if (index == chapters.size())
                    return;
                currentIndex = index++;
                currentURL = chapters.get(currentIndex).getURL();
            }
            //解析章节页面数据
            try {
                chapter = chapterParser(currentURL);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            //将解析数据写入章节列表中
            synchronized (chapters) {
                Chapter realChapter = chapters.get(currentIndex);
                realChapter.setTitle(chapter.getTitle());
                realChapter.setContent(chapter.getContent());
            }
        }
    }
}
