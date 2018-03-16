package com.ice.retrofitrxjavademo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.utils.AlertUtil;
import com.ice.retrofitrxjavademo.utils.PreferenceCache;
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
    @BindView(R.id.iv_hand_switch)
    ImageView mIvHandSwitch;
    @BindView(R.id.ll_setting_hand)
    LinearLayout mLlSettingHand;
    @BindView(R.id.view_second)
    View mViewSecond;
    @BindView(R.id.iv_fingerprint_switch)
    ImageView mIvFingerprintSwitch;
    private FingerprintIdentify mFingerprintIdentify;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_security);
    }

    @Override
    protected void initializeView() {
        mFingerprintIdentify = new FingerprintIdentify(this);
        Glide.with(SecurityActivity.this)
                .load(R.mipmap.alaska)
                //  .transforms(new BitmapCircleTransformation(SecurityActivity.this))
                .into(mIvIconMain);
    }

    @Override
    protected void initializeListener() {

    }

    @Override
    protected void initializeData() {
        //处理页面
        initData();
    }


    @OnClick({R.id.iv_hand_switch, R.id.ll_setting_hand, R.id.iv_fingerprint_switch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_hand_switch:
                if (!PreferenceCache.getGestureFlag()) {
                    Intent intent = new Intent(SecurityActivity.this, SettingPatternPswActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    Intent close_intent = new Intent(SecurityActivity.this, ClosePatternPswActivity.class);
                    //等于1为删除密码
                    close_intent.putExtra("gestureFlg", 1);
                    startActivityForResult(close_intent, 1);
                }
                break;
            case R.id.ll_setting_hand:
                Intent intent = new Intent(SecurityActivity.this, ClosePatternPswActivity.class);
                //等于2为修改密码
                intent.putExtra("gestureFlg", 2);
                startActivityForResult(intent, 1);
                break;
            case R.id.iv_fingerprint_switch:
                if (mFingerprintIdentify.isHardwareEnable()) {
                    //指纹可用
                    if (mFingerprintIdentify.isFingerprintEnable()) {
                        if (PreferenceCache.getFingerFlg()) {
                            //取消指纹
                            mIvFingerprintSwitch.setImageResource(R.mipmap.auto_bidding_on);
                            AlertUtil.t(SecurityActivity.this, "指纹验证功能已取消");
                            PreferenceCache.putFingerFlg(false);
                        } else {
                            //打开指纹
                            mIvFingerprintSwitch.setImageResource(R.mipmap.auto_bidding_off);
                            AlertUtil.t(SecurityActivity.this, "指纹验证功能已打开");
                            PreferenceCache.putFingerFlg(true);
                        }

                    } else {
                        AlertUtil.t(SecurityActivity.this, "请先去录入指纹");
                    }
                } else {
                    AlertUtil.t(SecurityActivity.this, "辣鸡手机，用不了指纹，换手机吧");
                }
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

    private void initData() {
        if (PreferenceCache.getGestureFlag()) {
            mIvHandSwitch.setImageResource(R.mipmap.auto_bidding_off);
            mLlSettingHand.setVisibility(View.VISIBLE);
            mViewSecond.setVisibility(View.VISIBLE);
        } else {
            mIvHandSwitch.setImageResource(R.mipmap.auto_bidding_on);
            mLlSettingHand.setVisibility(View.GONE);
            mViewSecond.setVisibility(View.GONE);
        }

        if (PreferenceCache.getFingerFlg()) {
            mIvFingerprintSwitch.setImageResource(R.mipmap.auto_bidding_off);
        } else {
            mIvFingerprintSwitch.setImageResource(R.mipmap.auto_bidding_on);
        }
    }
}