package com.ice.retrofitrxjavademo.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.utils.AlertUtil;
import com.ice.retrofitrxjavademo.utils.PreferenceCache;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

public class VerifyFingerActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private FingerprintIdentify mFingerprintIdentify;
    private TextView tv_hand_login;
    private Dialog dialog;
    private boolean isClick;
    private TextView tv_mian_login;
    private ImageView iv_finger_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_finger);
        iv_finger_icon = (ImageView) findViewById(R.id.iv_finger_icon);
        Glide.with(VerifyFingerActivity.this)
                .load(R.mipmap.alaska)
                //                .transforms(new BitmapCircleTransformation(VerifyFingerActivity.this))
                .into(iv_finger_icon);
        mFingerprintIdentify = new FingerprintIdentify(this);

        //弹出dialog，自动弹出
        dialog = new Dialog(VerifyFingerActivity.this, R.style.Dialog);
        dialog.setContentView(R.layout.item_dialog);
        dialog.setCancelable(false);
        dialog.show();
        TextView tv = (TextView) dialog.findViewById(R.id.tv_cancel);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        if (dialog.isShowing()) {
            initVerify();
        }
        tv_hand_login = (TextView) findViewById(R.id.tv_hand_login);
        if (PreferenceCache.getGestureFlag()) {
            tv_hand_login.setVisibility(View.VISIBLE);
        } else {
            tv_hand_login.setVisibility(View.GONE);
        }

        tv_hand_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClosePatternPswActivity.class);
                //等于3为认证成功
                intent.putExtra("gestureFlg", 3);
                startActivity(intent);
                finish();
            }
        });
        imageView = (ImageView) findViewById(R.id.iv_verify_finger);
        imageView.setOnClickListener(this);
        tv_mian_login = (TextView) findViewById(R.id.tv_mian_login);
        tv_mian_login.setOnClickListener(this);

    }

    private void initVerify() {
        mFingerprintIdentify.startIdentify(4, new BaseFingerprint.FingerprintIdentifyListener() {
            @Override
            public void onSucceed() {
                Intent intent = new Intent(VerifyFingerActivity.this, SecurityActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onNotMatch(int availableTimes) {
                AlertUtil.t(VerifyFingerActivity.this, "验证失败，您还有" + availableTimes + "次机会");


            }

            @Override
            public void onFailed(boolean isDeviceLocked) {
                AlertUtil.t(VerifyFingerActivity.this, "验证失败指纹暂被锁定");
                isClick = true;
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }


            }

            @Override
            public void onStartFailedByDeviceLocked() {
                AlertUtil.t(VerifyFingerActivity.this, "验证失败，指纹已被锁定");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_verify_finger:
                if (!isClick) {
                    if (!dialog.isShowing()) {
                        dialog.show();
                    }
                }

                break;
            case R.id.tv_mian_login:
                AlertUtil.t(VerifyFingerActivity.this, "到自己登录页面重新登陆,处理逻辑");
                break;
        }
    }
}
