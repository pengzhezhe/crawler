package com.pzz.crawler.dl;

import com.pzz.crawler.Chapter;
import com.pzz.crawler.Constant;
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
        List<Chapter> chapters = parserCategory();
        DlparserThread t = new DlparserThread(chapters, 0);
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

        //创建文件
        String userHome = System.getProperty("user.home");
        File file = new File(userHome + "/Desktop/dl.txt");
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        PrintStream pout = new PrintStream(out);

        //把章节列表中的数据写入文件
        for (Chapter chapter : chapters) {
            System.out.println("Write:" + chapter.getTitle());
            pout.println(chapter.getTitle());
            pout.println(chapter.getContent());
        }
    }

    /**
     * 解析目录
     *
     * @return 章节列表
     * @throws IOException
     */
    private static List<Chapter> parserCategory() throws IOException {
        List<Chapter> category = new ArrayList<Chapter>();
        Document document = Jsoup.connect(Constant.DL_CATEGORY_URL).get();
        Elements dd = document.getElementsByTag("dd");
        //去掉最新章节，从第一章开始
        dd.subList(0, 12).clear();
        Elements hrefs = dd.select("a[href]");
        for (int i = 0; i < hrefs.size(); i++) {
            String href = Constant.DL_BASE_URL + hrefs.get(i).attr("href");
            Chapter chapter = new Chapter(href, null, null);
            category.add(i, chapter);
        }
        return category;
    }
}
