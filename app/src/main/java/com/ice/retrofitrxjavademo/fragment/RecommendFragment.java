package com.ice.retrofitrxjavademo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.view.ChildViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ICE on 2017/7/20.
 */
public class RecommendFragment extends Fragment {
    int imgs[] = {
            R.mipmap.guidance1,
            R.mipmap.guidance2,
            R.mipmap.guidance3
    };
    private ChildViewPager mChildViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_recommand, container, false);
        mChildViewPager = (ChildViewPager) view.findViewById(R.id.child_viewPager);
        {
            super.onCreate(savedInstanceState);
            final List<View> list = new ArrayList<>();
            for (int i = 0; i < imgs.length; i++) {
                ImageView iv = new ImageView(getActivity());
                iv.setImageResource(imgs[i]);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                list.add(iv);
            }
            //这是滑动到最后一页加载详情页面
            // View last = LayoutInflater.from(getActivity()).inflate(R.layout.last_page, null);
            // list.add(last);
            PagerAdapter adapter = new PagerAdapter() {
                @Override
                public int getCount() {
                    return list.size();
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    container.addView(list.get(position));
                    return list.get(position);
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }
            };

            mChildViewPager.setAdapter(adapter);
        }
        return view;
    }

}
