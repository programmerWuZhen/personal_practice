package com.example.personal_wuzhen.bean;

public class Music {
    private  String music_name;
    private  String music_singer;

    public Music(String music_name, String music_singer) {
        this.music_name = music_name;
        this.music_singer = music_singer;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public void setMusic_singer(String music_singer) {
        this.music_singer = music_singer;
    }

    public String getMusic_name() {
        return music_name;
    }

    public String getMusic_singer() {
        return music_singer;
    }
}
