package com.ice.retrofitrxjavademo.utils;

import android.support.annotation.NonNull;

import com.ice.retrofitrxjavademo.callback.DownLoadCallBack;
import com.ice.retrofitrxjavademo.http.Networks;
import com.ice.retrofitrxjavademo.interceptor.DownLoadInterceptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by ICE on 2018/1/19.
 */

public class DownloadUtils {

    private static final String TAG = "DownloadUtils";

    private static final int DEFAULT_TIMEOUT = 15;

    private Retrofit retrofit;

    private DownLoadCallBack listener;

    private String baseUrl;

    private String downloadUrl;

    public DownloadUtils(String baseUrl, DownLoadCallBack listener) {

        this.baseUrl = baseUrl;
        this.listener = listener;

        DownLoadInterceptor mInterceptor = new DownLoadInterceptor(listener);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 开始下载
     *
     * @param url
     * @param filePath
     * @param subscriber
     */
    public void download(@NonNull String url, final String filePath, Observer subscriber) {

        listener.onStartDownLoad();

        // subscribeOn()改变调用它之前代码的线程
        // observeOn()改变调用它之后代码的线程
        //        Networks.getInstance().getApiService()
        //                .download(url)
        //                .subscribeOn(Schedulers.io())
        //                .unsubscribeOn(Schedulers.io())
        //                .map(new Func1<ResponseBody, InputStream>() {
        //
        //                    @Override
        //                    public InputStream call(ResponseBody responseBody) {
        //                        return responseBody.byteStream();
        //                    }
        //                })
        //                .observeOn(Schedulers.computation()) // 用于计算任务
        //                .doOnNext(new Action1<InputStream>() {
        //                    @Override
        //                    public void call(InputStream inputStream) {
        //
        //                        writeFile(inputStream, filePath);
        //
        //                    }
        //                })
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(subscriber);
        Networks.getInstance().getApiService()
                .download(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, InputStream>() {
                    @Override
                    public InputStream apply(ResponseBody responseBody) throws Exception {
                        return responseBody.byteStream();
                    }
                })
                .observeOn(Schedulers.computation())
                .doOnNext(new Consumer<InputStream>() {
                    @Override
                    public void accept(InputStream inputStream) throws Exception {
                        writeFile(inputStream, filePath);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(subscriber);
    }

    /**
     * 将输入流写入文件
     *
     * @param inputString
     * @param filePath
     */
    private void writeFile(InputStream inputString, String filePath) {

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024];

            int len;
            while ((len = inputString.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            inputString.close();
            fos.close();

        } catch (FileNotFoundException e) {
            listener.onFail("FileNotFoundException");
        } catch (IOException e) {
            listener.onFail("IOException");
        }

    }
}
