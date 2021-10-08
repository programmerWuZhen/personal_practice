package com.example.personal_wuzhen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private View view1,view2,view3,view4;//用来接收三个page布局，实现图片的滚动
    private List<View> viewList;//存放前面的三个布局
    private MyPagerAdapter pagerAdapter;//自定义pageview适配器
    ViewPager viewPager;
    TextView text_userName;
    Button btn_birdayquery,btn_match,btn_bgmusic,btn_about;
    private Cursor cursor;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        init();
        //设置各个按钮的单击事件
        btnclick();
        /*initplay();*/
    }




    private void init() {
        viewPager=findViewById(R.id.viewpager);
        text_userName=findViewById(R.id.text_userName);
        btn_birdayquery=findViewById(R.id.btn_birdayquery);
        btn_match=findViewById(R.id.btn_match);
        btn_bgmusic=findViewById(R.id.btn_bgmusic);
        btn_about=findViewById(R.id.btn_about);
        //将前面定义的布局转换为view对象
        LayoutInflater inflater=getLayoutInflater();
        view1=inflater.inflate(R.layout.page1,null);
        view2=inflater.inflate(R.layout.page2,null);
        view3=inflater.inflate(R.layout.page3,null);
        view4=inflater.inflate(R.layout.page4,null);
        //将前面的对象存入数组
        viewList=new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        pagerAdapter=new MyPagerAdapter(viewList);
        viewPager.setAdapter(pagerAdapter);
        text_userName.setText(getIntent().getStringExtra("username"));
    }
    private void btnclick() {
        btn_birdayquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BirthdayAssistant.class);
                startActivity(intent);
            }
        });
        btn_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usersex=getIntent().getStringExtra("usersex");
                Intent intent=new Intent(MainActivity.this,Match.class);
                intent.putExtra("usersex",usersex);
                startActivity(intent);
            }
        });
        btn_bgmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,BgMusic.class);
                startActivity(intent);
            }
        });
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,About.class);
                startActivity(intent);
            }
        });

    }
/*    private void initplay() {
        mediaPlayer=new MediaPlayer();
        while (cursor.moveToNext()){
        String songPath=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
        mediaPlayer.reset();
        try{
            mediaPlayer.setDataSource(songPath);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
            }
    }*/

}
