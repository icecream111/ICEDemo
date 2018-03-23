package com.ice.retrofitrxjavademo.activity;

import android.widget.CompoundButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.view.SwitchButton;

import butterknife.BindView;

/**
 * Created by ICE on 2018/3/23
 * 仿ios 按钮
 */
@Route(path = "/ice/activity/SwitchButtonActivity")
public class SwitchButtonActivity extends BaseActivity {


    @BindView(R.id.sb_ios)
    SwitchButton mSbIos;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_switchbutton);
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void initializeListener() {
        mSbIos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ToastUtils.showShort("开关状态-->打开");
                } else {
                    ToastUtils.showShort("开关状态-->关闭");
                }
            }
        });
    }

    @Override
    protected void initializeData() {
    }


}



