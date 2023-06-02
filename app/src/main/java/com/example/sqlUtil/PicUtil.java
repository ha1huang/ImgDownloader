package com.example.sqlUtil;

import com.example.dao.PicDao;
import com.example.db.MyDB;
import com.example.entity.Pic;
import com.example.imgdownloader.MainActivity;

import java.util.List;


public class PicUtil {
    private static final MyDB  database = MyDB.getInstance(MainActivity.getInstance());
    private static final PicDao picDao = database.picDao();
    //保存图片
    public static void saveImage(String url, byte[] imageBytes) {
        Pic pic = new Pic();
        pic.setUrl(url);
        pic.setPicBytes(imageBytes);
        picDao.insert(pic);
    }
    //获取图片
    public static Pic getImage(String url) {
        return picDao.getPicByName(url);
    }
    //获取所有图片
    public static List<Pic> getImages(){
        return picDao.getAllPic();
    }
    //删除图片
    public static void deleteImage(String url){
        picDao.delete(url);
    }


}
