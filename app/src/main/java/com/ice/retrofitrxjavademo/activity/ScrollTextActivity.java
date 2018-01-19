package com.ice.retrofitrxjavademo.activity;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;

import butterknife.BindView;
import wellijohn.org.animtv.AnimTextView;

/**
 * Created by ICE on 2018/1/5.
 * 滚动数字显示
 */
@Route(path = "/ice/activity/ScrollTextActivity")
public class ScrollTextActivity extends BaseActivity {

    @BindView(R.id.atv)
    AnimTextView mAtv;
    private boolean toggle = false;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_scroll_text);
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void initializeListener() {
        mAtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle) {
                    mAtv.setText(235689.35);
                    toggle = false;
                } else {
                    mAtv.setText(123456.38);
                    toggle = true;
                }
            }
        });
    }

    @Override
    protected void initializeData() {
        mAtv.setText(123456.38);
    }

}
