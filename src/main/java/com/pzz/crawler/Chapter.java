package com.pzz.crawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chapter implements Comparable<Chapter> {
    String URL;
    String title;
    String content;

    public Chapter() {
    }

    public Chapter(String URL, String title, String content) {
        this.URL = URL;
        this.title = title;
        this.content = content;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Chapter chapter) {
        Pattern pattern1 = Pattern.compile("第\\d+章");
        Matcher matcher1 = pattern1.matcher(title);
        Pattern pattern2 = Pattern.compile("第\\d+章");
        Matcher matcher2 = pattern2.matcher(chapter.title);
        int a = 0, b = 0;
        while (matcher1.find()) {
            a = Integer.parseInt(matcher1.group().replace("第", "").replace("章", ""));
        }
        while (matcher2.find()) {
            b = Integer.parseInt(matcher2.group().replace("第", "").replace("章", ""));
        }
        return a > b ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "URL='" + URL + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
