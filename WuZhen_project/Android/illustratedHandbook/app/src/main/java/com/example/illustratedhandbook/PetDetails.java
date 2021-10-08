package com.example.illustratedhandbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PetDetails extends AppCompatActivity {
    TextView textName,textEngName,textLife,textPrice,textNation,textDes,textFeedPoints,textEasyOfDisease,textFeature;
    ImageView imgPetDetailes;
    private String category;
    private int petID;//用来接收从其他界面传来的宠物名
    private String adress;//用来接收请求地址
    private String imgURL;//获取图片
    private static final String TAG = "PetDetails";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);
        //绑定控件
        initView();
        //获取从之前页面传来的地址与关键字信息
        category=getIntent().getStringExtra("category");
        petID=getIntent().getIntExtra("petid",1);
        adress=getIntent().getStringExtra("adress");
        imgURL=getIntent().getStringExtra("imgurl");
        //创建Form表单
        RequestBody requestBody=new FormBody.Builder()
                                .add("apiKey","PH4zdtfeefa61952a990cd71ce41251878c6c9bc1828bc6")
                                .add("petID",Integer.toString(petID))
                                .build();
        //发送请求
        OkHttpHelp.sendOkHttpRequest(adress,requestBody, new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PetDetails.this,"网络请求失败",Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                        final String JsonStr=response.body().string();
                        if(category.equals("aquatic")){
                            JTBDetailes jtbDetailes= new Gson().fromJson(JsonStr,new TypeToken<JTBDetailes>(){}.getType());
                            final JTBDetailes.ResultBean resultBean=jtbDetailes.getResult();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textName.setText("中文名："+resultBean.getName());
                                    textEngName.setText("英文名："+resultBean.getEngName());
                                    textLife.setText("寿命："+resultBean.getLife());
                                    textPrice.setText(resultBean.getPrice());
                                    textNation.setText("        " +resultBean.getNation());
                                    textDes.setText("        "+resultBean.getDes());
                                    textFeedPoints.setText("        "+resultBean.getFeedPoints());
                                    textFeature.setText("        "+resultBean.getFeature());
                                    textEasyOfDisease.setText("        "+resultBean.getEasyOfDisease());
                                    Glide.with(PetDetails.this).load(imgURL).into(imgPetDetailes);
                                }
                            });
                        }
                        else if(category.equals("retiles")){
                            JTBDetailesRetiles jtbDetailesRetiles= new Gson().fromJson(JsonStr,new TypeToken<JTBDetailesRetiles>(){}.getType());
                            final JTBDetailesRetiles.ResultBean resultBean=jtbDetailesRetiles.getResult();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textName.setText("中文名："+resultBean.getName());
                                    textEngName.setText("英文名："+resultBean.getEngName());
                                    textLife.setText("寿命："+resultBean.getLife());
                                    textPrice.setText(resultBean.getPrice());
                                    textNation.setText("        " +resultBean.getNation());
                                    textDes.setText("        "+resultBean.getDes());
                                    textFeedPoints.setText("        "+resultBean.getFeedPoints());
                                    textFeature.setText("        "+resultBean.getFeature());
                                    textEasyOfDisease.setText("        "+resultBean.getEasyOfDisease());
                                    Glide.with(PetDetails.this).load(imgURL).into(imgPetDetailes);
                                }
                            });
                        }
                        else if(category.equals("small")){
                            JTBDetailesSmall jtbDetailesSmall= new Gson().fromJson(JsonStr,new TypeToken<JTBDetailesSmall>(){}.getType());
                            final JTBDetailesSmall.ResultBean resultBean=jtbDetailesSmall.getResult();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textName.setText("中文名："+resultBean.getName());
                                    textEngName.setText("英文名："+resultBean.getEngName());
                                    textLife.setText("寿命："+resultBean.getLife());
                                    textPrice.setText(resultBean.getPrice());
                                    textNation.setText("        " +resultBean.getNation());
                                    textDes.setText("        "+resultBean.getDes());
                                    textFeedPoints.setText("        "+resultBean.getFeedPoints());
                                    textFeature.setText("        "+resultBean.getFeature());
                                    textEasyOfDisease.setText("        "+resultBean.getEasyOfDisease());
                                    Glide.with(PetDetails.this).load(imgURL).into(imgPetDetailes);
                                }
                            });
                        }
                        else {
                            return;
                        }


                    }
                });
    }

    private void initView() {
        textName=findViewById(R.id.text_view_name);
        textEngName=findViewById(R.id.text_view_engName);
        textLife=findViewById(R.id.text_view_life);
        textPrice=findViewById(R.id.text_view_price);
        textNation=findViewById(R.id.text_view_nation);
        textDes=findViewById(R.id.text_view_des);
        textFeedPoints=findViewById(R.id.text_view_feedpoints);
        textEasyOfDisease=findViewById(R.id.text_view_esayofdisease);
        textFeature=findViewById(R.id.text_view_feature);
        imgPetDetailes=findViewById(R.id.img_petdetailes);
    }
}
