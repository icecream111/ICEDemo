package com.ice.retrofitrxjavademo.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.adapter.MainAdapter;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.fragment.GameFragment;
import com.ice.retrofitrxjavademo.fragment.HomePageFragment;
import com.ice.retrofitrxjavademo.fragment.MovieFragment;
import com.ice.retrofitrxjavademo.fragment.MusicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ICE on 2018/2/28.
 */
@Route(path = "/ice/activity/BottomTabActivity")
public class BottomTabActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    private List<Fragment> mList; //ViewPager的数据源
    private BadgeItem badgeItem;//下标消息提示
    private String msgNum = "18";//消息数量

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_bottomtab);
    }

    @Override
    protected void initializeView() {
        initBottomNavigationBar();
        initViewPager();
    }

    @Override
    protected void initializeListener() {

    }

    @Override
    protected void initializeData() {

    }

    //初始化ViewPager
    private void initViewPager() {
        mList = new ArrayList<>();
        mList.add(new HomePageFragment());
        mList.add(new MusicFragment());
        mList.add(new MovieFragment());
        mList.add(new GameFragment());

        mViewPager.addOnPageChangeListener(this);
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mainAdapter); //视图加载适配器
    }

    //初始化底部导航条
    private void initBottomNavigationBar() {
        mBottomNavigationBar.setTabSelectedListener(this);
        badgeItem = new BadgeItem()
                .setHideOnSelect(true) //设置被选中时隐藏角标
                .setBackgroundColor(Color.RED)
                .setText(msgNum);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(R.color.black);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "首页").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "音乐").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.mipmap.ic_tv_white_24dp, "电影").setActiveColorResource(R.color.white).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_videogame_asset_white_24dp, "游戏").setActiveColorResource(R.color.white))
                .setFirstSelectedPosition(0)
                .setActiveColor("#ff0000") //设置选中的颜色
                .setInActiveColor("#FFFFFF")
                .setBarBackgroundColor("#000000")
                .initialise(); //所有的设置需在调用该方法前完成

    }

    @Override
    public void onTabSelected(int position) {
        //tab被选中
        mViewPager.setCurrentItem(position);

        badgeItem.setText(msgNum);

        if ("0".equals(msgNum))
            badgeItem.hide();
        if (position == 2)
            msgNum = "0";

        Log.i(TAG, "onTabSelected: 000--->" + position);
    }

    @Override
    public void onTabUnselected(int position) {
        Log.i(TAG, "onTabUnselected: 000--->" + position);
    }

    @Override
    public void onTabReselected(int position) {
        Log.i(TAG, "onTabReselected: 000--->" + position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //ViewPager滑动
        mBottomNavigationBar.selectTab(position);
        Log.i(TAG, "onPageSelected: 000--->" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
