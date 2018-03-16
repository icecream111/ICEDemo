package com.ice.retrofitrxjavademo.bean;

/**
 * Created by pc on 2018/3/16.
 * 问卷数据的bean
 */

public class PagerListBean {
    private String title;

    public PagerListBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "PagerListBean{" +
                "title='" + title + '\'' +
                '}';
    }
}
