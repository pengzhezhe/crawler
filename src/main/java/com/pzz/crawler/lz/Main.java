//package com.pzz.crawler.lz;
//
//import com.pzz.crawler.entity.Chapter;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        List<Chapter> chapters = new ArrayList();
//        List<String> urls = WorkThread.getLzCatalog("http://longzu5.co");
//        WorkThread t = new WorkThread(urls, chapters);
//
//        Thread t1 = new Thread(t);
//        Thread t2 = new Thread(t);
//        Thread t3 = new Thread(t);
//        Thread t4 = new Thread(t);
//
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//
//        t1.join();
//        t2.join();
//        t3.join();
//        t4.join();
//
//        String userHome = System.getProperty("user.home");
//        File file = new File(userHome + "/Desktop/dl4.txt");
//        if (!file.exists())
//            file.createNewFile();
//        FileOutputStream out = new FileOutputStream(file);
//        PrintStream pout = new PrintStream(out);
//
//        Collections.sort(chapters);
//        for (Chapter chapter : chapters) {
//            System.out.println(chapter.getTitle());
//            pout.println(chapter.getTitle());
//            pout.print("\t");
//            pout.println(chapter.getContent());
//        }
//    }
//}