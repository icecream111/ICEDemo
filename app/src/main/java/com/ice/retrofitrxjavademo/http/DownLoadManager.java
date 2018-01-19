package com.ice.retrofitrxjavademo.http;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by ICE on 2018/1/19.
 */

public class DownLoadManager {

    public static boolean writeResponseBodyToDisk(String url, ResponseBody body) {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        String SDPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        try {
            is = body.byteStream();
            long total = body.contentLength();
            File file = new File(SDPath, url.substring(url.lastIndexOf("/") + 1));
            fos = new FileOutputStream(file);
            long sum = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                sum += len;
                int progress = (int) (sum * 1.0f / total * 100);
                Log.d("h_bl", "progress=" + progress);
            }
            fos.flush();
            Log.d("h_bl", "文件下载成功");
            return true;
        } catch (IOException e) {
            return false;
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
}
