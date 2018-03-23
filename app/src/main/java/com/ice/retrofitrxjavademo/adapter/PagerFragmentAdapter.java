package com.ice.retrofitrxjavademo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.bean.PagerListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/3/16.
 * 问卷调查页面适配器
 */

public class PagerFragmentAdapter extends BaseQuickAdapter<PagerListBean, BaseViewHolder> {
    private Context mContext;
    private List<PagerListBean> mShowItems = new ArrayList<>();
    private int mPos;

    public PagerFragmentAdapter(Context context, @Nullable List<PagerListBean> data, int pos) {
        super(R.layout.item_pager_list, data);
        this.mContext = context;
        this.mShowItems = data;
        this.mPos = pos;
    }

    @Override
    protected void convert(BaseViewHolder helper, PagerListBean item) {
        PagerListBean bean = mShowItems.get(mPos);

        TextView tv_check = helper.getView(R.id.tv_check);//选中状态
        tv_check.setText("B");
        tv_check.setTextColor(Color.parseColor("#fff"));
        tv_check.setBackgroundResource(R.drawable.uncheck_bg);

        if (true) {

        }

        switch ("") {
            case "A":
                tv_check.setText("A");
                tv_check.setTextColor(Color.parseColor("#fff"));
                tv_check.setBackgroundResource(R.drawable.check_bg);
                tv_check.setTextColor(Color.parseColor("#515056"));
                tv_check.setBackgroundResource(R.drawable.uncheck_bg);
                break;
            case "B":
                tv_check.setText("B");
                break;
            case "C":
                tv_check.setText("C");
                break;
            case "D":
                tv_check.setText("D");
                break;
            case "E":
                tv_check.setText("E");
                break;
        }
    }
}
