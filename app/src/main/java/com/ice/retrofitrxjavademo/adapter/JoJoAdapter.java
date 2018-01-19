package com.ice.retrofitrxjavademo.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.bean.JoJoBean;

import java.util.List;

/**
 * Created by ICE on 2017/12/15.
 * 首页适配器
 */

public class JoJoAdapter extends BaseQuickAdapter<JoJoBean,BaseViewHolder>{


    public JoJoAdapter(Context context, @Nullable List<JoJoBean> data) {
        super(R.layout.item_jojo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JoJoBean bean) {
        helper.setText(R.id.tv_content,bean.getContent());
    }
}
