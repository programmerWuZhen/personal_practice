package com.example.personal_wuzhen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.personal_wuzhen.bean.MID_music;
import com.example.personal_wuzhen.bean.Music;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class BgMusic extends AppCompatActivity {
    RecyclerView recyclerView_bgmusic;
    List<MID_music> MID_musicList=new ArrayList<MID_music>();
    List<Music> musicList=new ArrayList<Music>();
    Button btn_stopPlay;
    MediaPlayer mediaPlayer=new MediaPlayer();
    BmobQuery <MID_music>bmobQuery;
    private static final String TAG = "BgMusic";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg_music);
        //控件初始化
        initview();
        //初始化比目后端云
        /*initBomob();*/
        //准备音乐并添加
        initmusic();

        /*btnclick();*/
    }

    private void initview() {
        recyclerView_bgmusic=findViewById(R.id.recycleview_bg);
        /*btn_stopPlay=findViewById(R.id.btn_stopplay);*/
    }

    /*private void initBomob() {
        Bmob.initialize(this, "89c17d0a96b057071ee2b1834796b576");
        bmobQuery=new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<MID_music>() {
            @Override
            public void done(List<MID_music> list, BmobException e) {
                if(e==null){
                   for(MID_music mid_musicx:list){
                       MID_music music=new MID_music(mid_musicx.getMusic_name(),mid_musicx.getMusic_url());
                       MID_musicList.add(music);
                   }
                    try{
                        mediaPlayer.setDataSource(MID_musicList.get(0).getMusic_url().getUrl().replaceAll(" ","%20"));
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mediaPlayer.start();
                            }
                        });
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }else{
                    Log.d(TAG,"视频的名称是："+"视频的地址是：");
                }
            }
        });


    }*/


    private void initmusic() {

        //获取设备中的歌曲
        Cursor cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);
        while (cursor.moveToNext()){
            String musicnamex=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String singernamex=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            Music musicx=new Music(musicnamex,singernamex);
            musicList.add(musicx);
        }
        cursor.close();
        MyRecyclerViewAdapter adapter=new MyRecyclerViewAdapter(musicList);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView_bgmusic.setLayoutManager(layoutManager);
        recyclerView_bgmusic.setAdapter(adapter);

    }

}
