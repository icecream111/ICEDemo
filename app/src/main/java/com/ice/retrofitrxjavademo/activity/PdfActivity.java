package com.ice.retrofitrxjavademo.activity;

import android.graphics.Canvas;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ICE on 2018/1/19.
 * pdf 下载预览
 */
@Route(path = "/ice/activity/PdfActivity")
public class PdfActivity extends BaseActivity implements OnPageChangeListener, OnLoadCompleteListener, OnDrawListener {

    @BindView(R.id.pdfview)
    PDFView mPdfview;

    private OkHttpClient okHttpClient;
    private String url = "http://www.gaofengzc.com/uploadFile/pdf/b.pdf";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //进度条的值
                    int i = msg.arg1;
                    // progressBar.setProgress(i);
            }

            if (msg.arg1 == 100) {
                displayFromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), url.substring(url.lastIndexOf("/") + 1)));
            }

        }
    };


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_pdf);
    }

    @Override
    protected void initializeView() {
        //   Networks.getInstance()
        //           .getApiService()
        //           .downloadFile(url)
        //           .subscribe(new Subscriber<ResponseBody>() {
        //               @Override
        //               public void onCompleted() {
        //
        //               }
        //
        //               @Override
        //               public void onError(Throwable e) {
        //
        //               }
        //
        //               @Override
        //               public void onNext(ResponseBody responseBody) {
        //                   if (DownLoadManager.writeResponseBodyToDisk(url, responseBody)) {
        //                       displayFromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), url.substring(url.lastIndexOf("/") + 1)));
        //                       Toast.makeText(PdfActivity.this, "Download is sucess", Toast.LENGTH_LONG).show();
        //                   }
        //               }
        //           });

        Request request = new Request.Builder().url(url).build();

        okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("h_bl", "onFailure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        InputStream is = null;
                        byte[] buf = new byte[2048];
                        int len = 0;
                        FileOutputStream fos = null;
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                        try {
                            is = response.body().byteStream();
                            long total = response.body().contentLength();
                            File file = new File(path, url.substring(url.lastIndexOf("/") + 1));
                            fos = new FileOutputStream(file);
                            long sum = 0;
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                                sum += len;
                                int progress = (int) (sum * 1.0f / total * 100);
                                Log.d("h_bl", "progress=" + progress);
                                Message msg = handler.obtainMessage();
                                msg.what = 1;
                                msg.arg1 = progress;
                                handler.sendMessage(msg);
                            }
                            fos.flush();
                            Log.d("h_bl", "文件下载成功");
                        } catch (Exception e) {
                        } finally {
                            try {
                                if (is != null)
                                    is.close();
                            } catch (IOException e) {
                            }
                            try {
                                if (fos != null)
                                    fos.close();
                            } catch (IOException e) {
                            }
                        }
                    }
                });
    }

    @Override
    protected void initializeListener() {

    }

    @Override
    protected void initializeData() {
    }

    /**
     * pdf文件展示
     *
     * @param file
     */
    private void displayFromFile(File file) {
        mPdfview.fromFile(file)   //设置pdf文件地址
                .defaultPage(1)         //设置默认显示第1页
                .onPageChange(this)     //设置翻页监听
                .onLoad(this)           //设置加载监听
                .onDraw(this)            //绘图监听
                .showMinimap(false)     //pdf放大的时候，是否在屏幕的右上角生成小地图
                .swipeVertical(false)  //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .enableSwipe(true)   //是否允许翻页，默认是允许翻
                // .pages( 2 ，5  )  //把2  5 过滤掉
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        Toast.makeText(PdfActivity.this, " " + page + " / " + pageCount, Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载完成回调
     *
     * @param nbPages 总共的页数
     */
    @Override
    public void loadComplete(int nbPages) {
        Toast.makeText(PdfActivity.this, "加载完成" + nbPages, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
        // Toast.makeText( MainActivity.this ,  "pageWidth= " + pageWidth + "
        // pageHeight= " + pageHeight + " displayedPage="  + displayedPage , Toast.LENGTH_SHORT).show();
    }

}



