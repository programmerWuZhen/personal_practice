package com.example.personal_wuzhen;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpHelp {
    public  static  void  sendOkHttpRequest(String adress,okhttp3.Callback callback){
        //通过okhttp的异步请求来实现数据的抓取
        //1.获得okhttpclient类的实例
        OkHttpClient client=new OkHttpClient();
        //2.创建request对象 并通过对属性设置目标网络的地址 请求方式等
        Request request=new Request.Builder()
                        .url(adress)
                        .build();
        //3.通过okhttpclient类的实例调用newcall方法来创建call对象，并把异步请求的结果送入回调接口
        client.newCall(request).enqueue(callback);
    }
}
