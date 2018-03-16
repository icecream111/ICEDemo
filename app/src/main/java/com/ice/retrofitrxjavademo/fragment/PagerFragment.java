package com.ice.retrofitrxjavademo.fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.activity.ViewPagerListActivity;
import com.ice.retrofitrxjavademo.adapter.PagerFragmentAdapter;
import com.ice.retrofitrxjavademo.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by pc on 2018/3/16.
 * 问卷的fragment
 */

public class PagerFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private FragmentActivity mActivity;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_pagerlist;
    }

    @Override
    protected void initializeView(View view) {
        mActivity = getActivity();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        PagerFragmentAdapter fragmentAdapter = new PagerFragmentAdapter(mActivity, ((ViewPagerListActivity) getActivity()).mShowItems);
        fragmentAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initializeListener() {

    }

    @Override
    protected void initializeData() {

    }

}
