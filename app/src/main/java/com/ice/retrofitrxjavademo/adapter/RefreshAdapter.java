package com.ice.retrofitrxjavademo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ice.retrofitrxjavademo.R;

import java.util.List;

/**
 * 刷新适配器
 */
public class RefreshAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public RefreshAdapter(@Nullable List<String> data) {
        super(R.layout.item_refresh, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content, item);
    }
}
