package com.pzz.crawler.dl;

import com.pzz.crawler.entity.Common;
import org.jsoup.Jsoup;

import java.io.IOException;

public class NetThread implements Runnable {
    private String url;

    public NetThread(String url) {
        this.url = url;
    }

    public void run() {
        while (true) {
            if (url.equals("http://www.jueshitangmen.info/3048/")) {
                Common.document = null;
                return;
            }
            try {
                synchronized (Common.document) {
                    Common.document = Jsoup.connect(url).get();
                    String nextPageUrl = Common.document.getElementById("pager_next").attr("href");
                    url = "http://www.jueshitangmen.info" + nextPageUrl;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
