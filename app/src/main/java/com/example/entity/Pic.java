package com.example.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pic")
public class Pic {
    //主键
    @PrimaryKey(autoGenerate = true)
    private int id;
    //照片url
    private String url;
    public byte[] picBytes;

//    public Pic() {
//    }
//    public Pic(String url, byte[] picBytes) {
//        this.url = url;
//        this.picBytes = picBytes;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPicBytes(byte[] picBytes) {
        this.picBytes = picBytes;
    }

    public String getUrl() {
        return url;
    }

    public byte[] getPicBytes() {
        return picBytes;
    }


}
