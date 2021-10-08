package com.example.personal_wuzhen.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class MID_music extends BmobObject {
    private String music_name;
    private BmobFile music_url;

    public MID_music(String music_name, BmobFile music_url) {
        this.music_name = music_name;
        this.music_url = music_url;
    }

    public MID_music(String tableName, String music_name, BmobFile music_url) {
        super(tableName);
        this.music_name = music_name;
        this.music_url = music_url;
    }

    public String getMusic_name() {
        return music_name;
    }

    public BmobFile getMusic_url() {
        return music_url;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public void setMusic_url(BmobFile music_url) {
        this.music_url = music_url;
    }
}
