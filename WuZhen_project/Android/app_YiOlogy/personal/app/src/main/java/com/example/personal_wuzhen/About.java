package com.example.personal_wuzhen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.MediaController;
import android.os.Build;
import android.os.Bundle;
import android.widget.VideoView;

public class About extends AppCompatActivity {
    private VideoView videoView;
    private android.widget.MediaController mediaController;;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        videoView=findViewById(R.id.video_view);
        mediaController=new MediaController(this);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.about);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}
