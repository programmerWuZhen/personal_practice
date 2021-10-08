package com.example.personal_wuzhen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class BirthdayAssistant extends AppCompatActivity {
/*    private TextView txt_lunarBirthday,txt_zodiac,txt_tgdz,txt_constellation,txt_bazi,txt_bazil,txt_wuxing,txt_wuxingl;*/
    DatePicker datePicker;
    TimePicker timePicker;
    Button btn_query;
    int bYear,bMonth,bDay,bHour;
    AlertDialog.Builder builder;
    private String str1,str2,str3,str4,str5,str6,str7,str8;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_assistant);
        init();
        getbirth();

    }

    private void init() {
/*        txt_lunarBirthday=findViewById(R.id.txt_lunarbirthday);
        txt_zodiac=findViewById(R.id.txt_zodiac);
        txt_tgdz=findViewById(R.id.txt_tgdz);
        txt_bazi=findViewById(R.id.txt_bazi);
        txt_bazil=findViewById(R.id.txt_bazil);
        txt_wuxing=findViewById(R.id.txt_wuxing);
        txt_wuxingl=findViewById(R.id.txt_wuxingl);
        txt_constellation=findViewById(R.id.txt_constellation);*/
        timePicker=findViewById(R.id.timePicker);
        datePicker=findViewById(R.id.datePicker);
        btn_query=findViewById(R.id.btn_query);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getbirth() {
        timePicker.setIs24HourView(true);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adress="http://apis.juhe.cn/birthEight/query";
                bYear=datePicker.getYear();
                bMonth=datePicker.getMonth();
                bDay=datePicker.getDayOfMonth();
                bHour= timePicker.getHour();
                str1="您的农历生日为:";
                str2="天干地支为:";
                str3="您的属相为:";
                str4="您的星座为:";
                str5="您的八字为:";
                str6="您属:";
                str7="您的五行为:";
                str8="五行缺:";
                adress=adress+"?year="+bYear+"&month="+(bMonth+1)+"&day="+bDay+"&hour="+bHour+"&key=07b6496ae484ee5f3c81e526cccc34d6";
                //调用工具类的方法抓取网页数据并处理显示
                OkHttpHelp.sendOkHttpRequest(adress, new okhttp3.Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        final String responseData=response.body().string();
                        try{
                            final JSONObject jsonObject=new JSONObject(responseData);
                            JSONObject jsonData=jsonObject.getJSONObject("result");
                            str1=str1+jsonData.getString("year")+"年"+jsonData.getString("ImonthCn")+jsonData.getString("IDayCn");
                            str2=str2+jsonData.getString("gzYear")+"年"+jsonData.getString("gzMonth")+"月"+jsonData.getString("gzDay")+"日";
                            str3+=jsonData.getString("Animal");
                            str4+=jsonData.getString("astro");
                            JSONObject jsonEightAll=jsonData.getJSONObject("eightAll");
                            String list=jsonEightAll.getString("eight");
                            str5=str5+list;
                            str6=str6+jsonEightAll.getString("shu");
                            JSONObject jsonFiveAll=jsonData.getJSONObject("fiveAll");
                            str7=str7+jsonFiveAll.getString("five");
                            str8=str8+jsonFiveAll.getString("lose");

                        }catch (Exception e){

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                builder=new AlertDialog.Builder(BirthdayAssistant.this);
                                builder.setIcon(R.drawable.icon_shengchen);
                                builder.setTitle("您的生辰信息如下：");
                                builder.setMessage(str1+"\n"+str2+"\n"+str3+"\n"+str4+"\n"+str5+"\n"+str6+"\n"+str7+"\n"+str8+"\n");
                                builder.setPositiveButton("继续查询", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.setNegativeButton("退出查询", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                               /* txt_lunarBirthday.setText(str1);
                                txt_tgdz.setText(str2);
                                txt_zodiac.setText(str3);
                                txt_constellation.setText(str4);
                                txt_bazi.setText(str5);
                                txt_bazil.setText(str6);
                                txt_wuxing.setText(str7);
                                txt_wuxingl.setText(str8);*/
                            }
                        });
                    }
                });
            }
        });
    }
}
