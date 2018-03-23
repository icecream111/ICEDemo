package com.ice.retrofitrxjavademo.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.activity.ViewPagerListActivity;
import com.ice.retrofitrxjavademo.adapter.PagerFragmentAdapter;
import com.ice.retrofitrxjavademo.base.BaseFragment;
import com.ice.retrofitrxjavademo.bean.PagerListBean;

import butterknife.BindView;

/**
 * Created by pc on 2018/3/16.
 * 问卷的fragment
 */

public class PagerFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private FragmentActivity mActivity;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_pagerlist;
    }

    /**
     * 入口
     *
     * @param pos
     * @return
     */
    public static PagerFragment newInstance(int pos) {
        PagerFragment myFragment = new PagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    protected void initializeView(View view) {
        mActivity = getActivity();
        Bundle bundle = getArguments();
        int pos = bundle.getInt("pos");
        PagerListBean bean = ((ViewPagerListActivity) getActivity()).mShowItems.get(pos);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        PagerFragmentAdapter fragmentAdapter = new PagerFragmentAdapter(mActivity, ((ViewPagerListActivity) getActivity()).mShowItems, pos);
        fragmentAdapter.notifyDataSetChanged();
        /**
         * 问题的title
         */
        mTvTitle.setText("");
    }

    @Override
    protected void initializeListener() {

    }

    @Override
    protected void initializeData() {

    }

}
