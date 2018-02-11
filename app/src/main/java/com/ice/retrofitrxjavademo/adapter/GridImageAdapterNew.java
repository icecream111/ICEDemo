package com.ice.retrofitrxjavademo.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ice.retrofitrxjavademo.R;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DebugUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ICE on 2018/1/23.
 */

public class GridImageAdapterNew extends BaseQuickAdapter<LocalMedia, BaseViewHolder> {
    private Context mContext;
    private List<LocalMedia> mShowItems = new ArrayList();
    /**
     * 点击添加图片跳转
     */
    private onAddPicClickListener mOnAddPicClickListener;

    public interface onAddPicClickListener {
        void onAddPicClick();
    }

    public GridImageAdapterNew(Context context, @Nullable List<LocalMedia> data) {
        super(R.layout.item_pic, data);
        this.mContext = context;
        this.mShowItems = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, LocalMedia item) {
        int position = helper.getAdapterPosition();
        ImageView mImg = helper.getView(R.id.fiv);
        LinearLayout ll_del = helper.getView(R.id.ll_del);
        TextView tv_duration = helper.getView(R.id.tv_duration);

        if (mShowItems.size() < 9)
            mImg.setImageResource(R.mipmap.addimg_1x);
        else
            mImg.setVisibility(View.INVISIBLE);
        /**
         * 添加图片按钮回调
         */
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnAddPicClickListener.onAddPicClick();
            }
        });

        if (position == mShowItems.size() - 1)
            ll_del.setVisibility(View.VISIBLE);
        else
            ll_del.setVisibility(View.INVISIBLE);

        ll_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = helper.getAdapterPosition();
                // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
                // 通过源码分析应该是bindViewHolder()暂未绘制完成导致，知道原因的也可联系我~感谢
                if (index != RecyclerView.NO_POSITION) {
                    mShowItems.remove(index);
                    notifyItemRemoved(index);
                    notifyItemRangeChanged(index, mShowItems.size());
                    DebugUtil.i("delete position:", index + "--->remove after:" + mShowItems.size());
                }
            }
        });
    }
}
