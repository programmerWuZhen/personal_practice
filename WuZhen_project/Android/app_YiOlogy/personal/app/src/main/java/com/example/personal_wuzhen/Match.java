package com.example.personal_wuzhen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class Match extends AppCompatActivity {
    Spinner spin_xingzuoself,spin_xingzuoelse;
    private TextView txt_zhishu,txt_bizhong,txt_xiangyue,txt_tcdj,txt_jieguo,txt_lianai,txt_zhuyi;
    Button btn_matchQuery,btn_mantchBack;
    String []xingzuo={"水瓶","双鱼","白羊","金牛","双子","巨蟹","狮子","处女","天秤","天蝎","射手","摩羯"};
    String userSex;
    String male_xingzuo,female_xingzuo;
    String adress;
    private String str1,str2,str3,str4,str5,str6,str7,str8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //初始化数据
        inti();
        //设置spinner被选中时的事项
        spinnerclick();
        //设置按钮的单击事件
        btnclick();
    }
    //初始化数据
    private void inti() {
        spin_xingzuoself=findViewById(R.id.spinner_xingzuoself);
        spin_xingzuoelse=findViewById(R.id._spinner_xingzuoelse);
        btn_matchQuery=findViewById(R.id.btn_match_query);
        btn_mantchBack=findViewById(R.id.btn_match_back);
        txt_zhishu=findViewById(R.id.txt_zhishu);
        txt_bizhong=findViewById(R.id.txt_bizhong);
        txt_xiangyue=findViewById(R.id.txt_xiangyue);
        txt_tcdj=findViewById(R.id.txt_tcdj);
        txt_jieguo=findViewById(R.id.txt_jieguo);
        txt_lianai=findViewById(R.id.txt_lianai);
        txt_zhuyi=findViewById(R.id.txt_zhuyi);
        //为spinner控件准备数据
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Match.this,android.R.layout.simple_spinner_item,xingzuo);
        spin_xingzuoself.setAdapter(adapter);
        spin_xingzuoelse.setAdapter(adapter);
        userSex=getIntent().getStringExtra("usersex");
    }
    //设置各个spinner控件被选中时的事件
    private void spinnerclick() {
        spin_xingzuoself.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView=(TextView) view;
                if(userSex.equals("男")){
                    male_xingzuo=textView.getText().toString();
                }
                else{
                    female_xingzuo=textView.getText().toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin_xingzuoelse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView=(TextView)view;
                if(userSex.equals("女")){
                    male_xingzuo=textView.getText().toString();
                }
                else{
                    female_xingzuo=textView.getText().toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    //查询按钮的单击事件
    private void btnclick() {
        btn_matchQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Match.this,"男性的星座为"+male_xingzuo+"女性星座为"+female_xingzuo,Toast.LENGTH_LONG).show();
                str1="配对指数:";
                str2="配对比重:";
                str3="两情相悦指数:";
                str4="天长地久指数:";
                str5="结果描述:";
                str6="恋爱建议:";
                str7="注意事项:";
                adress="http://apis.juhe.cn/xzpd/query";
                adress=adress+"?men="+male_xingzuo+"&women="+female_xingzuo+"&key=a9e8255dc9b0091431602c5ee154decc";
                OkHttpHelp.sendOkHttpRequest(adress, new okhttp3.Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        final String responseData=response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            JSONObject jsonData=jsonObject.getJSONObject("result");
                            str1+=jsonData.getString("zhishu");
                            str2+=jsonData.getString("bizhong");
                            str3+=jsonData.getString("xiangyue");
                            str4+=jsonData.getString("tcdj");
                            str5+=jsonData.getString("jieguo");
                            str6+=jsonData.getString("lianai");
                            str7+=jsonData.getString("zhuyi");

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txt_zhishu.setText(str1);
                                txt_bizhong.setText(str2);
                                txt_xiangyue.setText(str3);
                                txt_tcdj.setText(str4);
                                txt_jieguo.setText(str5);
                                txt_lianai.setText(str6);
                                txt_zhuyi.setText(str7);
                            }
                        });
                    }
                });
            }
        });
        btn_mantchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
