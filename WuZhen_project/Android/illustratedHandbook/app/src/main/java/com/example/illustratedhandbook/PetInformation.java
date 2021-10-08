package com.example.illustratedhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.illustratedhandbook.Adapter.PetsInformationAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PetInformation extends AppCompatActivity {

    private RecyclerView recyclerView_PetInfromation;//用来显示宠物信息
    private JsonToBean.ResultBean resultBeans=new JsonToBean.ResultBean();//获取网络返回的（宠物信息数组+总数）的类
    private String adress;//获取前面页面送来的网络请求地址信息
    private String keyWord;//获取之前页面传的关键字信息
    private String category;//获取宠物类别
    private String page,pageSize;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_information);
        //绑定控件
        recyclerView_PetInfromation=findViewById(R.id.recycler_petinformation);
        initData();//网络请求 并处理返回的数据

    }

    private void initData() {
        //获取从之前页面传来的地址、关键字信息、类别、页面及页面大小
        adress=getIntent().getStringExtra("adress");
        keyWord=getIntent().getStringExtra("keyWord");
        category=getIntent().getStringExtra("category");
        page=Integer.toString(getIntent().getIntExtra("page",1));
        pageSize=Integer.toString(getIntent().getIntExtra("pageSize",20));
        userName=getIntent().getStringExtra("userName");
        RequestBody requestBody=new FormBody.Builder()
                                    .add("apiKey","PH4zdtfeefa61952a990cd71ce41251878c6c9bc1828bc6")
                                    .add("keyword",keyWord)
                                    .add("page",page)
                                    .add("pageSiaze",pageSize)
                                    .build();
        OkHttpHelp.sendOkHttpRequest(adress, requestBody,new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PetInformation.this,"网络请求失败",Toast.LENGTH_LONG).show();
                    }
                });
                
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String jsonStr=response.body().string();
                final JsonToBean jsonToBean=new Gson().fromJson(jsonStr,new TypeToken<JsonToBean>(){}.getType());
                if (jsonToBean.getStatusCode().equals("000000")){
                    resultBeans=jsonToBean.getResult();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //设置适配器
                            PetsInformationAdapter adapter=new PetsInformationAdapter(PetInformation.this,category,userName,resultBeans.getPetFamilyList());
                            StaggeredGridLayoutManager st=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                            recyclerView_PetInfromation.setLayoutManager(st);
                            recyclerView_PetInfromation.setAdapter(adapter);
                        }
                    });
                }
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(PetInformation.this,jsonToBean.getDesc(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
