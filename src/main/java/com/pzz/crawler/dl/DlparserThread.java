package com.pzz.crawler.dl;

import com.pzz.crawler.Chapter;
import com.pzz.crawler.ParserThread;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class DlparserThread extends ParserThread {

    public DlparserThread(List<Chapter> chapters, int index) {
        super(chapters, index);
    }

    public Chapter chapterParser(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Chapter chapter = new Chapter();
        String title = document.getElementsByClass("bookname").get(0).getElementsByTag("h1").text();
        Elements elements = document.getElementById("content").getElementsByTag("p");
        StringBuilder content = new StringBuilder();
        for (Element e : elements) {
            if (!e.text().equals("喜欢斗罗大陆4终极斗罗请大家收藏：(www.jueshitangmen.info)斗罗大陆4终极斗罗绝世唐门更新速度最快。"))
                content.append("\t").append(e.text()).append("\n");
        }
        System.out.println("Parser:" + title);
        chapter.setURL(url);
        chapter.setTitle(title);
        chapter.setContent(content.toString());
        return chapter;
    }


}
