package com.ice.retrofitrxjavademo.activity;

import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by ICE on 2018/3/1.
 */
@Route(path = "/ice/activity/DaoJiShiActivity")
public class DaoJiShiActivity extends BaseActivity {
    @BindView(R.id.tv_hour)
    TextView mTvHour;
    @BindView(R.id.tv_minute)
    TextView mTvMinute;
    @BindView(R.id.tv_second)
    TextView mTvSecond;
    @BindView(R.id.ll_xsqg)
    LinearLayout mLlXsqg;
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;

    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                if (mHour < 10) {
                    mTvHour.setText("0" + mHour + "");
                } else {
                    mTvHour.setText("0" + mHour + "");
                }
                if (mMin < 10) {
                    mTvMinute.setText("0" + mMin + "");
                } else {
                    mTvMinute.setText(mMin + "");
                }
                if (mSecond < 10) {
                    mTvSecond.setText("0" + mSecond + "");
                } else {
                    mTvSecond.setText(mSecond + "");
                }
            }
        }
    };


    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }


    @Override
    protected void setContentView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_daojishi);
        startRun();
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void initializeListener() {

    }

    @Override
    protected void initializeData() {

    }

}