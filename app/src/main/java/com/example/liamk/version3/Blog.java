package com.example.liamk.version3;

/**
 * Created by liamk on 16/02/2017.
 */

public class Blog {

    private String title;
    private String desc;

    public Blog(){

    }
    
    public Blog(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
