package com.ice.retrofitrxjavademo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.constants.Constants;
import com.ice.retrofitrxjavademo.utils.ACache;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ICE on 2018/3/5.
 * 手势指纹密码
 */
@Route(path = "/ice/activity/SecurityActivity")
public class SecurityActivity extends BaseActivity {

    @BindView(R.id.iv_icon_main)
    ImageView mIvIconMain;
    @BindView(R.id.tv_handle_title)
    TextView mTvHandleTitle;
    @BindView(R.id.iv_hand_switch)
    ImageView mIvHandSwitch;
    @BindView(R.id.ll_fingure_psd)
    LinearLayout mLlFingurePsd;
    @BindView(R.id.ll_change_fingure_psd)
    LinearLayout mLlChangeFingurePsd;
    @BindView(R.id.iv_fingerprint_switch)
    ImageView mIvFingerprintSwitch;
    private FingerprintIdentify mFingerprintIdentify;
    private ACache aCache;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_security);
    }

    @Override
    protected void initializeView() {
        mFingerprintIdentify = new FingerprintIdentify(this);
        Glide.with(SecurityActivity.this)
                .load(R.mipmap.alaska)
                .into(mIvIconMain);
    }

    @Override
    protected void initializeListener() {
        aCache = ACache.get(this);
    }

    @Override
    protected void initializeData() {
        initData();
    }


    @OnClick({R.id.iv_hand_switch, R.id.ll_fingure_psd, R.id.ll_change_fingure_psd, R.id.iv_fingerprint_switch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_hand_switch:
                break;
            case R.id.ll_fingure_psd:
                String gesturePassword = aCache.getAsString(Constants.GESTURE_PASSWORD);
                if (gesturePassword == null || "".equals(gesturePassword)) {//设置手势密码
                    Intent intent = new Intent(this, CreateGestureActivity.class);
                    startActivityForResult(intent, 1);
                } else {//关闭手势密码
                    Intent close_intent = new Intent(this, GestureLoginActivity.class);
                    //等于1为删除密码
                    close_intent.putExtra("gestureFlg", 1);
                    startActivityForResult(close_intent, 1);
                }
                break;
            case R.id.ll_change_fingure_psd:
                break;
            case R.id.iv_fingerprint_switch:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            initData();
        }
    }

    /**
     * 按钮状态修改
     */
    private void initData() {
        String gesturePassword = aCache.getAsString(Constants.GESTURE_PASSWORD);
        if (gesturePassword == null || "".equals(gesturePassword)) {
            mIvHandSwitch.setImageResource(R.mipmap.auto_bidding_off);
            mLlChangeFingurePsd.setVisibility(View.GONE);
            mTvHandleTitle.setText("开启手势密码");
        } else {
            mIvHandSwitch.setImageResource(R.mipmap.auto_bidding_on);
            mLlChangeFingurePsd.setVisibility(View.VISIBLE);
            mTvHandleTitle.setText("关闭手势密码");
        }
    }
}