package com.example.JsonUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class UrlListParser {
    private static Gson gson;

    public static List<String> getUrlList(String urls) {
        gson = new Gson();
        List<String> list = gson.fromJson(urls, new TypeToken<List<String>>() {
        }.getType());
        for (String fruit : list) {
            System.out.println(fruit);
        }
        return list;
    }
}
