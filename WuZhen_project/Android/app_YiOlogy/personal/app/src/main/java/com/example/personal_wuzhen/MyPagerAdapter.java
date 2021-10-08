package com.example.personal_wuzhen;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter {
    private List<View> pageList;

    public MyPagerAdapter(List<View> pageList) {
        this.pageList = pageList;
    }
    //滑动页数
    @Override
    public int getCount() {
        return pageList.size();
    }
    //是否是当前的视图
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    //从容器中删除指定视图
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(pageList.get(position));
    }

    @NonNull
    @Override
    //加载指定位置的视图到容器中
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(pageList.get(position));
        //返回当前的视图
        return pageList.get(position);
    }
}
