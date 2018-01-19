package com.ice.retrofitrxjavademo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment父类
 * Created by ICE on 2017/12/15.
 */
public abstract class BaseFragment extends Fragment {
    protected String TAG = getClass().getSimpleName();
    private Unbinder mUnbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initializeView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeListener();
        initializeData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    /**
     * 1. 设置布局
     */
    protected abstract int getLayoutID();

    /**
     * 2. 初始化布局
     */
    protected abstract void initializeView(View view);

    /**
     * 3. 初始化事件
     */
    protected abstract void initializeListener();

    /**
     * 4. 初始化ui数据
     */
    protected abstract void initializeData();

}
