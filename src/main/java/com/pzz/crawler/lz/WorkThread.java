//package com.pzz.crawler.lz;
//
//import com.pzz.crawler.entity.Chapter;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WorkThread implements Runnable {
//
//    private List<String> urls;
//
//    private List<Chapter> chapters;
//
//    public WorkThread(List<String> urls, List<Chapter> chapters) {
//        this.urls = urls;
//        this.chapters = chapters;
//    }
//
//    public static List<String> getLzCatalog(String url) throws IOException {
//        List<String> urlList = new ArrayList<>();
//        Document document = Jsoup.connect(url).get();
//        Element booklist = document.getElementsByClass("booklist").get(0);
//        Elements elements = booklist.getElementsByTag("a");
//        for (Element e : elements) urlList.add(e.attr("href"));
//        return urlList;
//    }
//
//    public static Chapter chapterParser(String url) {
//        try {
//            Document document = Jsoup.connect(url).get();
//            Element title = document.getElementsByClass("post-title").get(0);
//            Element content = document.getElementsByClass("post-body").get(0);
//            return new Chapter(title.text(), content.text().replace(" ", "\n\t"));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new Chapter();
//        }
//    }
//
//    @Override
//    public void run() {
//        String url;
//        while (true) {
//            synchronized (this) {
//                if (urls.size() == 0)
//                    return;
//                url = urls.get(0);
//                urls.remove(0);
//            }
//            Chapter chapter = chapterParser(url);
//            System.out.println(chapter.getTitle());
//            synchronized (this) {
//                chapters.add(chapter);
//            }
//        }
//    }
//}
