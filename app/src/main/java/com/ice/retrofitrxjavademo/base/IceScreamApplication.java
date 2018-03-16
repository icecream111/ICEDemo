package com.ice.retrofitrxjavademo.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by ICE on 2017/11/10.
 * Application
 */

public class IceScreamApplication extends MultiDexApplication {
    private static IceScreamApplication getInstance;
    public static final boolean ISDEBUG = true;         //是否是debug模式

    @Override
    public void onCreate() {
        super.onCreate();
        getInstance = this;
        initRefreshHeader();
        Utils.init(getInstance);
        initQbsdk();
        /**
         * 阿里路由框架初始化
         */
        if (ISDEBUG) {
            ARouter.openLog();
        }
        ARouter.init(getInstance);
    }

    public static IceScreamApplication getInstance() {
        return getInstance;
    }

    /**
     * 初始化SmartRefreshLayout
     * 设置全局的上拉加载下拉刷新样式
     */
    private void initRefreshHeader() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new ClassicsHeader(context);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(context);//指定为经典Footer，默认是 BallPulseFooter
            }
        });
    }

    /**
     * 初始化X5内核webview
     */
    private void initQbsdk() {
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtils.e("加载内核是否成功:" + b);
            }
        });
    }


}
