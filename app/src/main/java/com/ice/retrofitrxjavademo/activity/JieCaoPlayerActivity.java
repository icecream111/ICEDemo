package com.ice.retrofitrxjavademo.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by ICE on 2018/2/6.
 */
@Route(path = "/ice/activity/JieCaoPlayerActivity")
public class JieCaoPlayerActivity extends BaseActivity {
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard mVideoplayer;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_jiecao);
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void initializeListener() {

    }

    @Override
    protected void initializeData() {
        mVideoplayer.setUp("http://www.xinjiyuancps.com/xinjiyuanimg/upload/images/circle/buy/video/2018.02.06/8c13f4f4-cffb-462b-9cbe-3dbcf164c050.mp4",
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");

        Glide.with(this)
                .load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")
                .into( mVideoplayer.thumbImageView);

    }

}
