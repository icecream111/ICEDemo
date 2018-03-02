package com.ice.retrofitrxjavademo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.adapter.RedTabAdapter;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.fragment.GameFragment;
import com.ice.retrofitrxjavademo.fragment.HomePageFragment;
import com.ice.retrofitrxjavademo.fragment.MovieFragment;
import com.ice.retrofitrxjavademo.fragment.MusicFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by ICE on 2018/2/28.
 */
@Route(path = "/ice/activity/RedPointTabActivity")
public class RedPointTabActivity extends BaseActivity {
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private ArrayList<String> list_title = new ArrayList<>();//tab名集合
    private ArrayList<Fragment> list_fragment = new ArrayList<>();//fragment集合
    private RedTabAdapter mRedTabAdapter;
    private TextView mTv_tab_title;
    private ImageView mRedPoint;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_redtabpoint);
    }

    @Override
    protected void initializeView() {
        mTabLayout.setTabTextColors(getResources().getColor(R.color.black), getResources().getColor(R.color.green_default));
        list_fragment.add(new GameFragment());
        list_fragment.add(new MovieFragment());
        list_fragment.add(new MusicFragment());
        list_fragment.add(new HomePageFragment());

        list_title.add("游戏");
        list_title.add("电影");
        list_title.add("音乐");
        list_title.add("主页");

        mRedTabAdapter = new RedTabAdapter(this, getSupportFragmentManager(), list_fragment, list_title);
        mViewPager.setAdapter(mRedTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);

        //设置小红点
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            View tabView = mRedTabAdapter.getTabView(i);
            ImageView imageView = (ImageView) tabView.findViewById(R.id.iv_tab_red);

            /**
             * 在这里判断每个TabLayout的内容是否有更新，来设置小红点是否显示
             */
            mTabLayout.getTabAt(i).setCustomView(tabView);
        }

    }

    @Override
    protected void initializeListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                /**在这里记录TabLayout选中后内容更新已读标记**/

                View customView = tab.getCustomView();
                customView.findViewById(R.id.iv_tab_red).setVisibility(View.GONE);
                TextView textView = (TextView) customView.findViewById(R.id.tv_tab_title);
                textView.setTextColor(getResources().getColor(R.color.red));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                TextView textView = (TextView) customView.findViewById(R.id.tv_tab_title);
                textView.setTextColor(getResources().getColor(R.color.black));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initializeData() {

    }

    /**
     * 是否显示小圆点
     *
     * @param isShow 根据后台返的字段
     */
    private void isShowRedPoint(boolean isShow) {
        for (int i = 0; i < list_title.size(); i++) {
            View tabView = mRedTabAdapter.getTabView(i);
            mRedPoint = (ImageView) tabView.findViewById(R.id.iv_tab_red);
            mTv_tab_title = (TextView) tabView.findViewById(R.id.tv_tab_title);
            if (i == 0)
                mTv_tab_title.setTextColor(getResources().getColor(R.color.green_default));

            if (isShow) {
                if (i == list_title.size() - 1) {
                    mRedPoint.setVisibility(View.VISIBLE);
                    mTabLayout.getTabAt(i).setCustomView(tabView);
                } else {
                    mRedPoint.setVisibility(View.GONE);
                    mTabLayout.getTabAt(i).setCustomView(tabView);
                }
            } else {
                mRedPoint.setVisibility(View.GONE);
                mTabLayout.getTabAt(i).setCustomView(tabView);
            }
        }
    }

}
