package com.ice.retrofitrxjavademo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ObjectUtils;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.view.ProgressWebview;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ICE on 2017/12/15.
 * Html 5 跳转页面
 */
@Route(path = "/ice/activity/MyWebViewActivity")
public class MyWebViewActivity extends BaseActivity {
    @BindView(R.id.wv)
    ProgressWebview mWebView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_webview_title)
    TextView mTvWebviewTitle;
    private WebSettings webSetting;
    private int start = -1;


    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }


    public static void newIntent(Context context, String url) {
        Intent intent = new Intent(context, MyWebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    public static void newIntent(Context context, String url, String title) {
        Intent intent = new Intent(context, MyWebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    public static void newIntent(Context context, String url, int start) {
        Intent intent = new Intent(context, MyWebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("start", start);
        context.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//（这个对宿主没什么影响，建议声明）
        setContentView(R.layout.activity_webview);
    }

    @Override
    protected void initializeView() {
        webSetting = mWebView.getSettings();
        mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
        mWebView.setVerticalScrollBarEnabled(false); //垂直不显示
        mWebView.setScrollContainer(false);
        webSetting.setJavaScriptEnabled(true);
        JavaScriptInterface JSInterface = new JavaScriptInterface(this);
        /**
         * js调用安卓本地方法
         */
        mWebView.addJavascriptInterface(JSInterface, "JSInterface");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        } else {
            webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }

        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);// 设置允许访问文件数据
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);//配置缓存类型
        webSetting.setDomStorageEnabled(true);
        webSetting.setDatabaseEnabled(true);

        //设置自适应屏幕，两者合用
        webSetting.setUseWideViewPort(true);        //将图片调整到适合webview的大小
        webSetting.setLoadWithOverviewMode(true);   // 缩放至屏幕的大小
        //  webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setVerticalScrollbarOverlay(false);
        mWebView.setHorizontalScrollbarOverlay(false);
        //加了这行代码，js弹框能弹出来了
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });

        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });

    }

    @Override
    protected void initializeListener() {
    }

    @Override
    protected void initializeData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");  //url
        String title = intent.getStringExtra("title");//标题
        mTvWebviewTitle.setText(ObjectUtils.isEmpty(title) ? "" : title);

        start = intent.getIntExtra("start", -1);
        if (ObjectUtils.isNotEmpty(url))
            mWebView.loadUrl(url);

    }

    /**
     * Js交互使用的内部类
     * 用于打电话
     */
    public class JavaScriptInterface {
        private Context mContext;

        public JavaScriptInterface(Context c) {
            mContext = c;
        }


        /**
         * 拨打电话
         *
         * @param phone
         */
        @JavascriptInterface
        public void call(String phone) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            if (ActivityCompat.checkSelfPermission(MyWebViewActivity.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);
        }
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        if (start == 0) {
            startActivity(new Intent(MyWebViewActivity.this, IceScreamMainActivity.class));
            finish();
        } else {
            finish();
        }
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (start == 0) {
                startActivity(new Intent(MyWebViewActivity.this, IceScreamMainActivity.class));
                finish();
            } else {
                finish();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
