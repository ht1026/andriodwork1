package com.example.work1;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Essay implements Serializable {
    private String title;
    private String content;
    private String time;

    public void setEssay(String title, String content) {
        this.title = title;
        this.content = content;
        setTime();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {

        return time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        time = formatter.format(date);
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
