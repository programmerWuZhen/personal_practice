package com.example.illustratedhandbook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.illustratedhandbook.db.MyDBHelper;

import java.util.Timer;
import java.util.TimerTask;

public class Register extends AppCompatActivity {
    EditText edt_userName,edt_userPw,edt_userPwConfirm;
    Button btn_back,btn_register;
    MyDBHelper myDBHelper;//创建一个数据库类文件
    SQLiteDatabase db;//创建一个可操作的数据对象
    Timer timer;//创建定时器
    TimerTask timerTask;//创建定时器任务
    private Handler handler=new Handler();//创建消息处理器
    int count=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //绑定控件
        init();

        //返回按钮的单击事件 结束当前activity 跳转到前一个（登录）界面
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,LogIn.class);
                startActivity(intent);
                finish();
            }
        });
        //注册按钮的单击事件 先判断两次密码是否一致 若一致则保存到数据库 弹出提示框并在三秒后跳转到登录界面。
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=edt_userName.getText().toString();
                String userPw=edt_userPw.getText().toString();
                String userPwConfirm=edt_userPwConfirm.getText().toString();
                Cursor cursor=db.rawQuery("select * from tb_UserInfo where userName=?",new String[]{userName});//先查询数据库中是否已经存在该用户名
                if(userName.length()<=0||userPw.length()<=0){
                    Toast.makeText(Register.this,"用户名或密码为空，请检查",Toast.LENGTH_LONG).show();
            }
                else if(cursor.moveToNext()) {//若查询到 即数据库中存在该用户名
                    Toast.makeText(Register.this, "用户名已存在，请重新填写用户名", Toast.LENGTH_LONG).show();
                }
                else{
                    if (userPw.equals(userPwConfirm)) {
                        ContentValues values = new ContentValues();//用来存放数据表中的一行数据
                        values.put("UserName", userName);
                        values.put("UserPw", userPw);
                        //将数据插入到数据表中
                        db.insert("tb_UserInfo", null, values);
                        Toast.makeText(Register.this, "您已注册成功，请牢记用户名和密码,将在3秒后返回登陆界面", Toast.LENGTH_LONG).show();
                        //倒计时函数
                        countdown();
                    } else {
                        Toast.makeText(Register.this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    private void init() {
        edt_userName=findViewById(R.id.edt_userName);
        edt_userPw=findViewById(R.id.edt_userPw);
        edt_userPwConfirm=findViewById(R.id.edt_userPwconfirm);
        btn_back=findViewById(R.id.btn_back);
        btn_register=findViewById(R.id.btn_register);
        myDBHelper=new MyDBHelper(Register.this);//数据库类文件实例化
        db=myDBHelper.getWritableDatabase();//调用该方法便可以向表中添加数据
    }
    //倒计时 用于在注册成功后自动跳转到登录界面
    private void countdown() {
        {
            timer=new Timer();
            timerTask=new TimerTask() {
                @Override
                public void run() {
                    //耗时的操作都放在子线程中
                    if(count!=0){
                        count--;
                    }else{
                        Intent intent=new Intent(Register.this,LogIn.class);
                        startActivity(intent);
                        finish();
                        timer.cancel();
                        timerTask.cancel();
                    }
                }
            };
            timer.schedule(timerTask,0,1000);
        }
    }

}
