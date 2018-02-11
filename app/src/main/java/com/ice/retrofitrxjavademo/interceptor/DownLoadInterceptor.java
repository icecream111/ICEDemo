package com.ice.retrofitrxjavademo.interceptor;

import com.ice.retrofitrxjavademo.bean.IceResponseBody;
import com.ice.retrofitrxjavademo.callback.DownLoadCallBack;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ICE on 2018/1/19.
 */

public class DownLoadInterceptor implements Interceptor {
    private DownLoadCallBack downLoadCallBack;

    public DownLoadInterceptor(DownLoadCallBack downLoadCallBack) {
        this.downLoadCallBack = downLoadCallBack;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(
                new IceResponseBody(response.body(), downLoadCallBack)).build();
    }
}
