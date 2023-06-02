package com.example.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.entity.Pic;

import java.util.List;

@Dao
public interface PicDao {
    @Insert
    void insert(Pic user);

//    @Update
//    void update(Pic user);

    @Query("delete from pic where url = :url ")
    void delete(String url);

    @Query("SELECT * FROM pic WHERE url = :url")
    Pic getPicByName(String url);

    @Query("select * from pic")
    List<Pic> getAllPic();


}
