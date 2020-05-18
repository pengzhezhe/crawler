package com.pzz.crawler;

public class Chapter {
    /**
     * 章节页面URL
     */
    String URL;

    /**
     * 章节标题
     */
    String title;

    /**
     * 章节内容
     */
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
    public String toString() {
        return "Chapter{" +
                "URL='" + URL + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
