package com.ice.retrofitrxjavademo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ice.retrofitrxjavademo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ICE on 2017/10/31.
 * 商家详情 厂家详情fragment适配器
 */

public class RedTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;                         //fragment列表
    private List<String> list_Title;                              //tab名的列表
    private Context mContext;

    public RedTabAdapter(Context context, FragmentManager fm, ArrayList<Fragment> list_fragment, ArrayList<String> list_title) {
        super(fm);
        this.list_fragment = list_fragment;
        this.list_Title = list_title;
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position % list_Title.size());
    }

    public View getTabView(int position) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.item_tab_layout, null);
        TextView tabTitle = (TextView) tabView.findViewById(R.id.tv_tab_title);
        tabTitle.setText(list_Title.get(position));
        return tabView;
    }
}
