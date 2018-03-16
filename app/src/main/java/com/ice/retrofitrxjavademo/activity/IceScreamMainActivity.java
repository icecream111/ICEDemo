package com.ice.retrofitrxjavademo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.adapter.JoJoAdapter;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.bean.JoJoBean;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ICE on 2017/11/10.
 * 主页页面
 */
//添加路由
// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = "/ice/activity/ChenBingMainActivity")
public class IceScreamMainActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.rv)
    RecyclerView mRv;
    private List<JoJoBean> mShowItems = new ArrayList();
    private JoJoAdapter mJoJoAdapter;
    private final int GET_CAMERA = 100;

    @Autowired
    public String name;
    @Autowired
    public long age;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);
        Log.e(TAG, "setContentView: params" + name + age);
    }

    @Override
    protected void initializeView() {
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mJoJoAdapter = new JoJoAdapter(this, mShowItems);
        mRv.setAdapter(mJoJoAdapter);
    }

    @Override
    protected void initializeListener() {
        mJoJoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                JoJoBean joJoBean = mShowItems.get(position);
                switch (joJoBean.getId()) {
                    case 1://二维码
                        getPermission();
                        break;
                    case 2://生日选择
                        ARouter.getInstance().build("/ice/activity/BirthDayActivity").navigation();
                        break;
                    case 3://一键锁屏
                        ARouter.getInstance().build("/ice/activity/LockScreenActivity").navigation();
                        break;
                    case 4://alertdialog
                        ARouter.getInstance().build("/ice/activity/DialogActivity").navigation();
                        break;
                    case 5://Annotations注解
                        ARouter.getInstance().build("/ice/activity/AnnotationsActivity").navigation();
                        break;
                    case 6://截屏
                        ARouter.getInstance().build("/ice/activity/ScreenActivity").navigation();
                        break;
                    case 7://ViewPager
                        ARouter.getInstance().build("/ice/activity/ViewPagerActivity").navigation();
                        break;
                    case 8://ScrollText
                        ARouter.getInstance().build("/ice/activity/ScrollTextActivity").navigation();
                        break;
                    case 9://pdf
                        ARouter.getInstance().build("/ice/activity/PdfActivity").navigation();
                        break;
                    case 10://downLoad
                        ARouter.getInstance().build("/ice/activity/DownLoadActivity").navigation();
                        break;
                    case 11://指定布局截屏
                        ARouter.getInstance().build("/ice/activity/ScreenCutActivity").navigation();
                        break;
                    case 12://图片选择器
                        ARouter.getInstance().build("/ice/activity/PictureSelectActivity").navigation();
                        break;
                    case 13://视频选择器
                        ARouter.getInstance().build("/ice/activity/VideoSelectActivity").navigation();
                        break;
                    case 14://节操播放器
                        ARouter.getInstance().build("/ice/activity/JieCaoPlayerActivity").navigation();
                        break;
                    case 15://垂直跑马灯
                        ARouter.getInstance().build("/ice/activity/MarqueeViewActivity").navigation();
                        break;
                    case 16://底部tab
                        ARouter.getInstance().build("/ice/activity/BottomTabActivity").navigation();
                        break;
                    case 17://tab小红点
                        ARouter.getInstance().build("/ice/activity/RedPointTabActivity").navigation();
                        break;
                    case 18://限时秒杀倒计时
                        ARouter.getInstance().build("/ice/activity/DaoJiShiActivity").navigation();
                        break;
                    case 19://导航栏
                        ARouter.getInstance().build("/ice/activity/TabActivity").navigation();
                        break;
                    case 20://问卷调查
                        ARouter.getInstance().build("/ice/activity/ViewPagerListActivity").navigation();
                        break;
                    case 21://手势 指纹密码
                        ARouter.getInstance().build("/ice/activity/SecurityActivity").navigation();
                        break;
                }
            }
        });
    }


    @Override
    protected void initializeData() {
        mShowItems.add(new JoJoBean(1, "二维码"));
        mShowItems.add(new JoJoBean(2, "日期选择"));
        mShowItems.add(new JoJoBean(3, "一键锁屏"));
        mShowItems.add(new JoJoBean(4, "AlertDialog"));
        mShowItems.add(new JoJoBean(5, "Annotations注解"));
        mShowItems.add(new JoJoBean(6, "截屏"));
        mShowItems.add(new JoJoBean(7, "ViewPager"));
        mShowItems.add(new JoJoBean(8, "ScrollText"));
        mShowItems.add(new JoJoBean(9, "pdf"));
        mShowItems.add(new JoJoBean(10, "downLoad"));
        mShowItems.add(new JoJoBean(11, "局部截屏"));
        mShowItems.add(new JoJoBean(12, "图片选择器"));
        mShowItems.add(new JoJoBean(13, "视频选择器"));
        mShowItems.add(new JoJoBean(14, "节操播放器"));
        mShowItems.add(new JoJoBean(15, "垂直跑马灯"));
        mShowItems.add(new JoJoBean(16, "底部tab"));
        mShowItems.add(new JoJoBean(17, "tab小红点"));
        mShowItems.add(new JoJoBean(18, "限时秒杀倒计时"));
        mShowItems.add(new JoJoBean(19, "Tab导航栏"));
        mShowItems.add(new JoJoBean(20, "问卷调查"));
        mShowItems.add(new JoJoBean(21, "手势指纹密码"));

        mJoJoAdapter.notifyDataSetChanged();
    }


    /**
     * 获取相应权限去扫码界面
     */
    private void getPermission() {
        //        RxPermissions rxPermissions = new RxPermissions(this);
        if (AndPermission.hasPermission(this, Manifest.permission.CAMERA)) {
            // 有权限，直接do anything.
            startActivityForResult(new Intent(this, CaptureActivity.class), 200);
            overridePendingTransition(0, 0);
        } else {
            // 申请权限。
            AndPermission.with(this)
                    .requestCode(GET_CAMERA)
                    .permission(Manifest.permission.CAMERA)
                    .send();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，第一个参数是当前Acitivity/Fragment，回调方法写在当前Activity/Framgent。
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    //获取权限成功的回调
    @PermissionYes(GET_CAMERA)
    private void getCameraYes(List<String> grantedPermissions) {
        startActivityForResult(new Intent(this, CaptureActivity.class), 200);
        overridePendingTransition(0, 0);
    }

    //获取权限失败的回调
    @PermissionNo(GET_CAMERA)
    private void getCameraNo(List<String> grantedPermissions) {
        getAppDetailSettingIntent(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200)
            if (resultCode == RESULT_OK || resultCode == 300) {
                Bundle bundle = data.getExtras();
                String result = bundle.getString("result");
                Log.e(TAG, "onActivityResult:result--- " + result);
                if (RegexUtils.isURL(result)) {
                    Log.e(TAG, "onActivityResult: //H5链接");
                    MyWebViewActivity.newIntent(this, result);
                } else {                            //非H5链接
                    ToastUtils.showShort(result);
                }
            }
    }

    /**
     * 跳转到权限设置界面
     */
    private void getAppDetailSettingIntent(Context context) {

        // vivo 点击设置图标>加速白名单>我的app
        //      点击软件管理>软件管理权限>软件>我的app>信任该软件
        //        Intent appIntent = context.getPackageManager().getLaunchIntentForPackage("com.iqoo.secure");
        //        if (appIntent != null) {
        //            context.startActivity(appIntent);
        //            floatingView = new SettingFloatingView(this, "SETTING", getApplication(), 0);
        //            floatingView.createFloatingView();
        //            return;
        //        }

        // oppo 点击设置图标>应用权限管理>按应用程序管理>我的app>我信任该应用
        //      点击权限隐私>自启动管理>我的app
        //        appIntent = context.getPackageManager().getLaunchIntentForPackage("com.oppo.safe");
        //        if (appIntent != null) {
        //            context.startActivity(appIntent);
        //            floatingView = new SettingFloatingView(this, "SETTING", getApplication(), 1);
        //            floatingView.createFloatingView();
        //            return;
        //        }

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        startActivity(intent);
    }

}
