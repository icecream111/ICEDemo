package com.ice.retrofitrxjavademo.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;

/**
 * Created by ICE on 2018/2/26.
 */
@Route(path = "/ice/activity/MarqueeViewActivity")
public class MarqueeViewActivity extends BaseActivity {

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_marquee);
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
