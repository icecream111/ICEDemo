package com.ice.retrofitrxjavademo.activity;

import android.content.Intent;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.constants.Constants;
import com.ice.retrofitrxjavademo.utils.ACache;
import com.ice.retrofitrxjavademo.utils.LockPatternUtil;
import com.ice.retrofitrxjavademo.view.LockPatternView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Sym on 2015/12/24.
 */
public class GestureLoginActivity extends BaseActivity {

    private static final String TAG = "LoginGestureActivity";
    @BindView(R.id.tv_user_account)
    TextView mTvUserAccount;
    @BindView(R.id.messageTv)
    TextView mMessageTv;
    @BindView(R.id.lockPatternView)
    LockPatternView mLockPatternView;
    @BindView(R.id.tv_forger_fingure_psd)
    TextView mTvForgerFingurePsd;
    @BindView(R.id.tv_login_other)
    TextView mTvLoginOther;
    private ACache aCache;
    private static final long DELAYTIME = 600l;
    private byte[] gesturePassword;
    private int gestureFlg;
    private int errorCount = 5;
    private final int DEFAULT = 0;
    private final int ERROR = 1;
    private final int CORRECT = 2;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_gesture_login);
    }

    @Override
    protected void initializeView() {
        aCache = ACache.get(GestureLoginActivity.this);
        //得到当前用户的手势密码
        gesturePassword = aCache.getAsBinary(Constants.GESTURE_PASSWORD);
        mLockPatternView.setOnPatternListener(patternListener);
        updateStatus(DEFAULT);
    }

    @Override
    protected void initializeListener() {
        String mMobileNum = SPUtils.getInstance().getString("userName");
        mTvUserAccount.setText(mMobileNum.substring(0, 3) + "****" + mMobileNum.substring(7, 11));
    }

    @Override
    protected void initializeData() {
        gestureFlg = getIntent().getIntExtra("gestureFlg", -1);
    }


    private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {

        @Override
        public void onPatternStart() {
            mLockPatternView.removePostClearPatternRunnable();
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> pattern) {
            if (pattern != null) {
                if (LockPatternUtil.checkPattern(pattern, gesturePassword)) {
                    updateStatus(CORRECT);
                } else {
                    updateStatus(ERROR);
                }
            }
        }
    };

    /**
     * 更新状态
     *
     * @param status
     */
    private void updateStatus(int status) {

        switch (status) {
            case DEFAULT:
                mMessageTv.setText("请输入手势密码");
                mMessageTv.setTextColor(getResources().getColor(R.color.grey_2c3036));
                mLockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case ERROR:
                mLockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                mLockPatternView.postClearPatternRunnable(DELAYTIME);
                /**
                 * 振动300ms
                 */
                Vibrator vibrator = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
                errorCount--;
                mMessageTv.setText("密码错误，还可以再输入" + errorCount + "次");
                mMessageTv.setTextColor(getResources().getColor(R.color.red_ef1917));
                if (errorCount == 0)
                    ToastUtils.showShort("超出错误次数");
                break;
            case CORRECT:
                mMessageTv.setText("输入正确");
                mMessageTv.setTextColor(getResources().getColor(R.color.grey_2c3036));
                mLockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                loginGestureSuccess();
                break;
        }
    }


    /**
     * 手势登录成功（去首页）
     */
    private void loginGestureSuccess() {
        if (gestureFlg == 1) {
            //删除密码
            ToastUtils.showShort("清空手势密码成功");
            aCache.remove(Constants.GESTURE_PASSWORD);
            this.finish();
        } else if (gestureFlg == 2) {
            //修改密码
            ToastUtils.showShort("验证手势密码成功,请重新设置");
            Intent intent = new Intent(GestureLoginActivity.this, CreateGestureActivity.class);
            startActivity(intent);
            this.finish();
        } else if (gestureFlg == 3) {
            this.finish();
        }
    }

    /**
     * 忘记手势密码（去账号登录界面）
     */
    @OnClick({R.id.iv_risk_back, R.id.tv_forger_fingure_psd, R.id.tv_login_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_risk_back:
                finish();
                break;
            case R.id.tv_forger_fingure_psd:
                break;
            case R.id.tv_login_other:
                aCache.remove(Constants.GESTURE_PASSWORD);
                ToastUtils.showShort("去登录");
                break;
        }
    }


}
