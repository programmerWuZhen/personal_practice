package com.example.personal_wuzhen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends AppCompatActivity {
    private TextView tex_count;
    private Timer timer;//创建定时器
    private TimerTask timerTask;//创建定时器任务
    private Handler handler=new Handler();//创建消息处理器
    private int count=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //绑定控件'
        initView();

        //数据初始化
        initData();
    }
    //初始化数据
    private void initView() {
        tex_count=findViewById(R.id.count);
    }
    //初始化数据
    private void initData() {
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                //耗时的操作都放在子线程中
                if(count!=0){
                    //timerTask为子线程 若要更新ui 只能在主线程中进行 而handller便可以接
                    //收子线程发送的数据，并用此数据配合更新UI
                    Message msg=new Message();
                    msg.what=1;//1表示消息信号
                    handler.sendMessage(msg);
                }else{
                    Message msg=new Message();
                    msg.what=0;
                    handler.sendMessage(msg);
                }

            }
        };
        timer.schedule(timerTask,0,1000);
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        count--;
                        tex_count.setText((count+1)+"");
                        break;
                    case 0:
                        Intent intent=new Intent(Welcome.this, LogIn.class);
                        startActivity(intent);
                        finish();
                        timer.cancel();
                        timerTask.cancel();
                        break;
                    default:break;
                }
            }
        };
    }
}

