package com.example.personal_wuzhen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personal_wuzhen.db.MyDBHelper;

public class LogIn extends AppCompatActivity {
    EditText edt_userName,edt_userPw;
    Button btn_logIn,btn_register;
    CheckBox check_rem;
    MyDBHelper myDBHelper;//创建一个数据库类文件
    SQLiteDatabase db;//创建一个可操作的数据库对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //绑定控件
        init();
        //登录按钮的单击事件
        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName=edt_userName.getText().toString();
                String inputPw=edt_userPw.getText().toString();
                if(inputName.length()<=0||inputPw.length()<=0){
                    Toast.makeText(LogIn.this,"用户名或密码为空，请检查",Toast.LENGTH_LONG).show();
                }else{
                    Cursor cursor=db.rawQuery("select * from tb_UserInfo where UserName=? and UserPw=?",new String[]{inputName,inputPw});
                    if(cursor.moveToNext()){//查询到时
                        //保存用户名和密码 若记住密码则下次登陆直接显示
                        SharedPreferences.Editor editor=getSharedPreferences("myinfo",0).edit();
                        editor.putString("userName",inputName);
                        editor.putString("userPW",inputPw);
                        editor.putBoolean("st",check_rem.isChecked());
                        editor.commit();
                        //跳转
                        Intent intent=new Intent(LogIn.this, MainActivity.class);
                        intent.putExtra("username",inputName);
                        intent.putExtra("usersex",cursor.getString(cursor.getColumnIndex("UserSex")));
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(LogIn.this,"用户名或密码错误，请重新登录或注册新账户",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogIn.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

        //显示用户名和密码
        displayinfo();


    }
    //绑定控件
    private void init() {
        edt_userName=findViewById(R.id.edt_userName);
        edt_userPw=findViewById(R.id.edt_userPw);
        btn_logIn=findViewById(R.id.btn_logIn);
        btn_register=findViewById(R.id.btn_register);
        check_rem=findViewById(R.id.check_rem);
        myDBHelper=new MyDBHelper(LogIn.this);
        db=myDBHelper.getWritableDatabase();
    }
    //判断是否记住密码
    public void displayinfo() {
        String strName=getSharedPreferences("myinfo",0).getString("userName","");
        String strPW=getSharedPreferences("myinfo",0).getString("userPW","");
        Boolean blCheck_rem=getSharedPreferences("myinfo",0).getBoolean("st",false);
        if (blCheck_rem==true){
            edt_userName.setText(strName);
            edt_userPw.setText(strPW);
            check_rem.setChecked(true);
        }else{
            edt_userName.setText("");
            edt_userPw.setText("");
            check_rem.setChecked(false);
        }
    }
}
