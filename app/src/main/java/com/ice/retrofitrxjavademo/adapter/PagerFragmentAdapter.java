package com.ice.retrofitrxjavademo.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.bean.PagerListBean;

import java.util.List;

/**
 * Created by pc on 2018/3/16.
 * 问卷调查页面适配器
 */

public class PagerFragmentAdapter extends BaseQuickAdapter<PagerListBean, BaseViewHolder> {
    private Context mContext;

    public PagerFragmentAdapter(Context context, @Nullable List<PagerListBean> data) {
        super(R.layout.item_pager_list, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PagerListBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
    }
}
