package com.example.personal_wuzhen;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personal_wuzhen.bean.MID_music;
import com.example.personal_wuzhen.bean.Music;

import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    /*List<MID_music> musicList;*/
    List<Music> musicList;

    MediaPlayer mediaPlayer=new MediaPlayer();;//定义一个播放器对象
    private static final String TAG = "MyRecyclerViewAdapter";
    public MyRecyclerViewAdapter(List<Music> getmusiclist) {
        musicList=getmusiclist;
    }

    @NonNull
    @Override
    //创建ViewHolder实例
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclebgm_item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();//获取当前点击的子项的位置
                Cursor cursor=view.getContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);
                cursor.moveToPosition(position);
                String musicPath=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                /*String musicPath=musicList.get(position).getMusic_url().getUrl();//获取音乐路径*/
                Log.d(TAG,"歌曲路径为"+musicPath);
                mediaPlayer.reset();//若之前有音频在播放，则先清空
                try{
                    mediaPlayer.setDataSource(musicPath);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        viewHolder.btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
            }
        });
        return viewHolder;
    }

    @Override
    //对recyclerview子项进行赋值
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        Music music=musicList.get(position);
        holder.textView_musicName.setText(music.getMusic_name());
        holder.textView_singerName.setText(music.getMusic_singer());
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View item_view;
        TextView textView_musicName,textView_singerName;
        Button btn_play,btn_stop;
        public ViewHolder(@NonNull View view) {
            super(view);
            textView_musicName=view.findViewById(R.id.music_name);
            textView_singerName=view.findViewById(R.id.singer_name);
            btn_play=view.findViewById(R.id.btn_play);
            btn_stop=view.findViewById(R.id.btn_stop);
            item_view=view;
        }
    }
}
