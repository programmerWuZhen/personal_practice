package com.example.illustratedhandbook;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.illustratedhandbook.Adapter.FragAdapter;
import com.example.illustratedhandbook.Fragment.FragmentAquaticPets;
import com.example.illustratedhandbook.Fragment.FragmentMyself;
import com.example.illustratedhandbook.Fragment.FragmentReptilePets;
import com.example.illustratedhandbook.Fragment.FragmentSmallPets;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton rb_toSmallPets,rb_toAquaticPets,rb_toReptilespets,rb_toPlants;
    private Fragment fragment_smallPets,fragment_aquaticPets,fragment_reptilePets,fragment_plants;
    private List<Fragment>myfragmentlist=new ArrayList<>();
    private int positon =0;//用来记录当前是第几页 默认零代表第一页
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定控件
        initview();
        //初始化数据
        initdata();
        //设置viewpager的滑屏事件
        initmove();
        initonclick();//单机导航栏按钮 碎片随即切换
    }

    private void initview() {
        viewPager=findViewById(R.id.view_pager);
        radioGroup=findViewById(R.id.radio_group);
        rb_toSmallPets=findViewById(R.id.btn_toSmallPets);
        rb_toAquaticPets=findViewById(R.id.btn_toAquaticPets);
        rb_toReptilespets=findViewById(R.id.btn_toRetilesPets);
        rb_toPlants=findViewById(R.id.btn_toPlant);
    }
    private void initdata() {
        //实例化碎片并添加到动态数组
        fragment_smallPets=new FragmentSmallPets();
        fragment_aquaticPets=new FragmentAquaticPets();
        fragment_reptilePets=new FragmentReptilePets();
        fragment_plants=new FragmentMyself();
        myfragmentlist.add(fragment_smallPets);
        myfragmentlist.add(fragment_aquaticPets);
        myfragmentlist.add(fragment_reptilePets);
        myfragmentlist.add(fragment_plants);
        //创建自定义适配器的实例
        FragAdapter adapter=new FragAdapter(this.getSupportFragmentManager(),myfragmentlist);
        //为ViewPager控件绑定适配器
        viewPager.setAdapter(adapter);
        //导航栏默认显示第一个Fragment
        ((RadioButton) radioGroup.getChildAt(positon)).setChecked(true);
    }

    private void initmove() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int i) {
                //用户滑屏到某个界面 导航栏的按钮也切换到对应界面
                ((RadioButton) radioGroup.getChildAt(i)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void initonclick() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_toSmallPets:
                        positon=0;
                        break;
                    case R.id.btn_toAquaticPets:
                        positon=1;
                        break;
                    case R.id.btn_toRetilesPets:
                        positon=2;
                        break;
                    case R.id.btn_toPlant:
                        positon=3;
                        break;
                    default:positon=0;break;
                }
                viewPager.setCurrentItem(positon);
            }
        });
    }
}
