package com.ice.retrofitrxjavademo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.adapter.Find_tab_Adapter;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.fragment.CollectionFragment;
import com.ice.retrofitrxjavademo.fragment.MonthFragment;
import com.ice.retrofitrxjavademo.fragment.RecommendFragment;
import com.ice.retrofitrxjavademo.fragment.HotToday;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ICE on 2017/12/29.
 * ViewPager 滑动页面
 */
@Route(path = "/ice/activity/ViewPagerActivity")
public class ViewPagerActivity extends BaseActivity {
    private TabLayout mTabLayout;                             //定义TabLayout
    private ViewPager mViewPager;                             //定义viewPager
    private FragmentPagerAdapter fAdapter;                    //定义adapter
    private List<Fragment> list_fragment;                     //定义要装fragment的列表
    private List<String> list_title;                          //tab名称列表

    private RecommendFragment hotRecommendFragment;           //热门推荐fragment
    private CollectionFragment hotCollectionFragment;         //热门收藏fragment
    private MonthFragment hotMonthFragment;                    //本月热榜fragment
    private HotToday hotToday;                                  //今日热榜fragment

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_vp);
    }

    @Override
    protected void initializeView() {
        mViewPager = (ViewPager) findViewById(R.id.diy_viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tab);

        //初始化各fragment
        hotRecommendFragment = new RecommendFragment();
        hotCollectionFragment = new CollectionFragment();
        hotMonthFragment = new MonthFragment();
        hotToday = new HotToday();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(hotRecommendFragment);
        list_fragment.add(hotCollectionFragment);
        list_fragment.add(hotMonthFragment);
        list_fragment.add(hotToday);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("热门推荐");
        list_title.add("热门收藏");
        list_title.add("本月热榜");
        list_title.add("今日热榜");

        //设置TabLayout的模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(3)));

        fAdapter = new Find_tab_Adapter(this.getSupportFragmentManager(), list_fragment, list_title);

        //viewpager加载adapter
        mViewPager.setAdapter(fAdapter);
        //mTabLayout.setViewPager(mViewPager);
        //TabLayout加载viewpager
        mTabLayout.setupWithViewPager(mViewPager);
        //mTabLayout.set
    }

    @Override
    protected void initializeListener() {

    }

    @Override
    protected void initializeData() {

    }
}
