package com.ice.retrofitrxjavademo.http;


import com.ice.retrofitrxjavademo.constants.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ICE on 2018/1/19
 */

public class Networks {

    private static final int DEFAULT_TIMEOUT = 5;

    private static Retrofit retrofit;

    private static ApiService mApiService;

    private static Networks mNetworks;

    public static Networks getInstance() {
        if (mNetworks == null) {
            mNetworks = new Networks();
        }
        return mNetworks;
    }

    public ApiService getApiService() {
        return mApiService == null ? configRetrofit(ApiService.class) : mApiService;
    }

    private <T> T configRetrofit(Class<T> service) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

    private OkHttpClient configClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return okHttpClient.build();
    }

}
