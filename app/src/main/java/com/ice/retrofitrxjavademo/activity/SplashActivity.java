package com.ice.retrofitrxjavademo.activity;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.callback.OnFinishListener;
import com.ice.retrofitrxjavademo.view.ProgressView;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.util.List;

/**
 * Created by ICE on 2017/12/15.
 * 启动页页面
 */
@Route(path = "/ice/activity/SplashActivity")
public class SplashActivity extends BaseActivity {
    private ProgressView mProgressView;
    private RelativeLayout mRlEnter;
    private final int READ_STATE = 100;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initializeView() {
        mRlEnter = (RelativeLayout) findViewById(R.id.rl_enter);
        mProgressView = (ProgressView) findViewById(R.id.progress);
        //跳过的点击事件
        mRlEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterMain();
            }
        });
        //设置进度条颜色
        mProgressView.setPaintColor("#ff0000");
        //开始倒计时
        mProgressView.startDownTime(4000, new OnFinishListener() {
            @Override
            public void onFinish() {
                enterMain();
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
     * 跳转到主页
     */
    private void enterMain() {
        getPermission();
    }

    /**
     * 获取相应权限
     */
    private void getPermission() {
        if (AndPermission.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // 有权限，直接do anything.
            // startActivity(new Intent(SplashActivity.this, ChenBingMainActivity.class));
            // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
            //   ARouter.getInstance()
            //           .build("/ice/activity/ChenBingMainActivity")
            //           .navigation();
            // 2. 跳转并携带参数
            ARouter.getInstance().build("/ice/activity/ChenBingMainActivity")
                    .withString("name", "888")
                    .withLong("age", 666L)
                    //   .withObject("key4", new Test("Jack", "Rose"))
                    .navigation();
            finish();
        } else {
            // 申请权限。
            AndPermission.with(this)
                    .requestCode(READ_STATE)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .send();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，第一个参数是当前Acitivity/Fragment，回调方法写在当前Activity/Framgent。
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    //获取权限成功的回调
    @PermissionYes(READ_STATE)
    private void getCameraYes(List<String> grantedPermissions) {
        startActivity(new Intent(SplashActivity.this, IceScreamMainActivity.class));
        finish();
    }

    //获取权限失败的回调
    @PermissionNo(READ_STATE)
    private void getCameraNo(List<String> grantedPermissions) {
        startActivity(new Intent(SplashActivity.this, IceScreamMainActivity.class));
        finish();
    }

}
