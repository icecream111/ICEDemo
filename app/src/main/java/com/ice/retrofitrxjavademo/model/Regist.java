package com.ice.retrofitrxjavademo.model;

/**
 * Created by ICE on 2017/11/10.
 */

public class Regist {

    /**
     * errorMessage : 登录成功!
     * resultCode : 1
     * logintoken : 97a4e629d7add72d9f6c7231062b1b76
     * professiontype : 1401
     */
    private String errorMessage;
    private int resultCode;
    private String logintoken;
    private String professiontype;
    private String name;//用户名字
    private String userID;//用户ID
    private String user_head_url;//用户头像
    private String account;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    private String identity;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getLogintoken() {
        return logintoken;
    }

    public void setLogintoken(String logintoken) {
        this.logintoken = logintoken;
    }

    public String getProfessiontype() {
        return professiontype;
    }

    public void setProfessiontype(String professiontype) {
        this.professiontype = professiontype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUser_head_url() {
        return user_head_url;
    }

    public void setUser_head_url(String user_head_url) {
        this.user_head_url = user_head_url;
    }

    @Override
    public String toString() {
        return "User{" +
                "errorMessage='" + errorMessage + '\'' +
                ", resultCode=" + resultCode +
                ", logintoken='" + logintoken + '\'' +
                ", professiontype='" + professiontype + '\'' +
                ", name='" + name + '\'' +
                ", userID='" + userID + '\'' +
                ", user_head_url='" + user_head_url + '\'' +
                ", account='" + account + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }
}
