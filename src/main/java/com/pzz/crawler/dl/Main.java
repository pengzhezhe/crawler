package com.pzz.crawler.dl;

import com.pzz.crawler.entity.Chapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<Chapter> chapters = getCategory();
        ParserThread t = new ParserThread(chapters);
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        String userHome = System.getProperty("user.home");
        File file = new File(userHome + "/Desktop/lz5.txt");
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        PrintStream pout = new PrintStream(out);

        for (Chapter chapter : chapters) {
            System.out.println(chapter.getTitle());
            pout.println(chapter.getTitle());
            pout.println(chapter.getContent());
        }
    }

    private static List<Chapter> getCategory() throws IOException {
        List<Chapter> category = new ArrayList<>();
        Document document = Jsoup.connect(Constant.categoryURL).get();
        Elements dd = document.getElementsByTag("dd");
        dd.subList(0, 12).clear();
        Elements hrefs = dd.select("a[href]");
        for (int i = 0; i < hrefs.size(); i++) {
            String href = Constant.baseURL + hrefs.get(i).attr("href");
            Chapter chapter = new Chapter(href, null, null);
            category.add(i, chapter);
        }
        return category;
    }
}
