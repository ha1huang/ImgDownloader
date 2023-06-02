package com.example.db;

import android.content.Context;
import android.util.Base64;

import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;

import com.example.dao.PicDao;
import com.example.entity.Pic;

@Database(entities = {Pic.class}, version = 1)
public abstract class MyDB extends RoomDatabase {
        private static MyDB instance;

        public abstract PicDao picDao();

        public static synchronized MyDB getInstance(Context context) {
//            if (context==null) return instance;
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        MyDB.class, "pic_storage")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()//important
                        .build();
            }
            return instance;
        }

        /**
         * 将字节数组转换为字符串
         */
        @TypeConverter
        public static String fromByteArray(byte[] bytes) {
            return Base64.encodeToString(bytes, Base64.DEFAULT);
        }

        /**
         * 将字符串转换为字节数组
         */
        @TypeConverter
        public static byte[] toByteArray(String string) {
            return Base64.decode(string, Base64.DEFAULT);
        }

}
