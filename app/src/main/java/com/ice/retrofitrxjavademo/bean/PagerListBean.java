package com.ice.retrofitrxjavademo.bean;

/**
 * Created by pc on 2018/3/16.
 * 问卷数据的bean
 */

public class PagerListBean {
    private String title;

    public ContentBean getContentBean() {
        return mContentBean;
    }

    public void setContentBean(ContentBean contentBean) {
        mContentBean = contentBean;
    }

    private ContentBean mContentBean;

    public PagerListBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class ContentBean {
        private String a;
    }

    @Override
    public String toString() {
        return "PagerListBean{" +
                "title='" + title + '\'' +
                '}';
    }
}
