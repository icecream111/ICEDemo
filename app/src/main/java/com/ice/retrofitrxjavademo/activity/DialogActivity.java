package com.ice.retrofitrxjavademo.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by ICE on 2017/12/18.
 * dialog页面
 */
@Route(path = "/ice/activity/DialogActivity")
public class DialogActivity extends BaseActivity {
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_dialog);
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


    @OnClick(R.id.tv_1)
    public void onViewClicked() {
        showDialog("17740886942");
    }

    /**
     * 显示拨打电话弹窗
     * @param tel
     */
    public void showDialog(final String tel) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("拨打电话 " + tel)
                .setPositiveButton("咨询", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
                        if (ActivityCompat.checkSelfPermission(DialogActivity.this, Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intent2);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

        //修改button的样式
        Button btnPositive = alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
        btnPositive.setTextColor(getResources().getColor(R.color.red));
        btnPositive.setTextSize(14);
        Button btnNegative = alertDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
        btnNegative.setTextColor(getResources().getColor(R.color.black));
        btnNegative.setTextSize(14);
    }
}
