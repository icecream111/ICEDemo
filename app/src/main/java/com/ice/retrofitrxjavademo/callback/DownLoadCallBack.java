package com.ice.retrofitrxjavademo.callback;

/**
 * Created by ICE on 2018/1/19.
 * 下载进度回调
 */

public interface DownLoadCallBack {
    /**
     * 开始下载
     */
    void onStartDownLoad();

    /**
     * 下载中
     *
     * @param progress 下载进度
     */
    void onProgress(int progress);

    /**
     * 下载完成
     */
    void onFinishDownLoad();

    /**
     * 下载失败
     *
     * @param errorinfo 失败信息
     */
    void onFail(String errorinfo);

}
