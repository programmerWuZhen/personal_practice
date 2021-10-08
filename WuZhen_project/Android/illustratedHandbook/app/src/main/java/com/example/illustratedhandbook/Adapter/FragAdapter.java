package com.example.illustratedhandbook.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragAdapter extends FragmentPagerAdapter {
    @NonNull
    //1.创建碎片数组
    private List<Fragment> myfragmentlist;
    //2.接收从activity传来的碎片数组
    public FragAdapter(@NonNull FragmentManager fm,  @NonNull List<Fragment> myfragmentlist) {
        super(fm);
        this.myfragmentlist = myfragmentlist;
    }
    //获取当前位置的碎片
    public Fragment getItem(int position) {
        return myfragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return myfragmentlist.size();
    }
}
