package com.ice.retrofitrxjavademo.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.adapter.FragmentListAdapter;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.ice.retrofitrxjavademo.bean.PagerListBean;
import com.ice.retrofitrxjavademo.fragment.PagerFragment;
import com.ice.retrofitrxjavademo.view.PreviewViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by pc on 2018/3/16.
 * 问卷调查页面
 */
@Route(path = "/ice/activity/ViewPagerListActivity")
public class ViewPagerListActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    PreviewViewPager mViewPager;
    @BindView(R.id.tv_previous)
    TextView mTvPrevious;
    @BindView(R.id.tv_current_page)
    TextView mTvCurrentPage;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    private List<Fragment> fragments = new ArrayList<>();       //问卷子页面
    public List<PagerListBean> mShowItems = new ArrayList();    //问卷数据

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_pager_list);
    }


    @Override
    protected void initializeView() {
        mTvPrevious.setVisibility(View.INVISIBLE);
        for (int i = 0; i < 10; i++) {
            PagerFragment pagerFragment = PagerFragment.newInstance( i);
            fragments.add(pagerFragment);
            PagerListBean pagerListBean = new PagerListBean("问卷调查第" + i + "题");
            mShowItems.add(pagerListBean);
        }

        FragmentListAdapter listAdapter = new FragmentListAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(listAdapter);
    }

    @Override
    protected void initializeListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mViewPager.getCurrentItem() == 0) {
                    mTvPrevious.setVisibility(View.INVISIBLE);
                } else if (mViewPager.getCurrentItem() == 9) {
                    mTvNext.setVisibility(View.INVISIBLE);
                } else {
                    mTvPrevious.setVisibility(View.VISIBLE);
                    mTvNext.setVisibility(View.VISIBLE);
                }
                mTvCurrentPage.setText((position + 1) + "/10");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initializeData() {

    }


    @OnClick({R.id.tv_previous, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_previous:      //上一页
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
                break;
            case R.id.tv_next:          //下一页
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                break;
        }
    }

}
