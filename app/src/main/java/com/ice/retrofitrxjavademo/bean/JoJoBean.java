package com.ice.retrofitrxjavademo.bean;

/**
 * Created by ICE on 2017/12/15.
 * 首页用的bean
 */

public class JoJoBean {
    private int id;
    private String content;

    public JoJoBean(int i, String content) {
        this.id = i;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "JoJoBean{" +
                "id=" + id +
                ", content=" + content +
                '}';
    }
}
