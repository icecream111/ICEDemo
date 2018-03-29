package com.ice.retrofitrxjavademo.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.adapter.RefreshAdapter;
import com.ice.retrofitrxjavademo.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 刷新加载页面
 */
@Route(path = "/ice/activity/RefreshActivity")
public class RefreshActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartLayout)
    SmartRefreshLayout mSmartLayout;
    private List<String> mShowItems = new ArrayList<>();
    private RefreshAdapter mRefreshAdapter;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_refresh);
    }

    @Override
    protected void initializeView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRefreshAdapter = new RefreshAdapter(mShowItems);
        mRecyclerView.setAdapter(mRefreshAdapter);
    }

    @Override
    protected void initializeListener() {

        mSmartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000);
            }
        });

        mSmartLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000);
            }
        });

    }


    @Override
    protected void initializeData() {

        for (int i = 0; i < 10; i++) {
            mShowItems.add("放羊的星星" + i);
        }
        mRefreshAdapter.notifyDataSetChanged();

    }

}
