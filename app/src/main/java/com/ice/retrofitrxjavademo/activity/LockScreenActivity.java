package com.ice.retrofitrxjavademo.activity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.broadcastreceiver.LockReceiver;

import butterknife.OnClick;

/**
 * Created by ICE on 2017/12/18.
 * 一键锁屏页面
 */
@Route(path = "/ice/activity/LockScreenActivity")
public class LockScreenActivity extends BaseActivity {
    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mComponentName;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_onekey_lock);
    }

    @Override
    protected void initializeView() {
        //获取设备管理器
        mDevicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        mComponentName = new ComponentName(this, LockReceiver.class);

    }

    @Override
    protected void initializeListener() {

    }

    @Override
    protected void initializeData() {

    }


    @OnClick(R.id.tv_onekey_lock)
    public void onViewClicked() {
        // 判断该组件是否有系统管理员的权限
        if (mDevicePolicyManager.isAdminActive(mComponentName)) {
            mDevicePolicyManager.lockNow(); //锁屏
        } else {
            activeManager();    //激活权限
        }
        //结束进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 激活设备管理器获取权限
     */
    private void activeManager() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "一键锁屏");
        startActivity(intent);
    }

}
