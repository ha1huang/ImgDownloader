package com.example.imgdownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.db.MyDB;
import com.example.downloader.Downloader;
import com.example.downloader.ImgDownloader;
import com.example.entity.Pic;
import com.example.http.HttpHelper;
import com.example.sqlUtil.PicUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;//对外暴露 this
    private Gson gson;

    public static MainActivity getInstance() {
        return instance;
    }


    private Button btn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里可以设计点击不要太频繁，在后端也可以设计点击频次（这里不演示）
                ImgDownloader.getInstance().download();
            }
        });



    }
}