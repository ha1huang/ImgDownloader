package com.example.downloader;

import com.example.JsonUtil.UrlListParser;
import com.example.db.MyDB;
import com.example.entity.Pic;
import com.example.http.HttpHandler;
import com.example.http.HttpHelper;
import com.example.sqlUtil.PicUtil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 * @author 黄天啸
 */
public class ImgDownloader implements HttpHandler, Downloader {
    private static ImgDownloader instance  = new ImgDownloader();
    private String picSetUrl = "https://shibe.online/api/shibes?count=10&urls=true&httpsUrls=true";
    private ImgDownloader(){}

    public static ImgDownloader getInstance(){return instance;}

    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    @Override
    public void download() {
        HttpHelper.getInstance().get(picSetUrl, ImgDownloader.getInstance());
    }

    @Override
    public Object handleGetResponse(Response response) throws IOException {
        String urls = response.body().string();
        System.out.printf("_____________________ " + response);
        List<String> urlList = UrlListParser.getUrlList(urls);

        for (String url : urlList) {
            HttpHelper.getInstance().get(url, new HttpHandler() {
                @Override
                public Object handleGetResponse(Response response) throws IOException {
                     executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            //数据中如果存在url则不下载了
                            if(PicUtil.getImage(url)!=null){
                                return;
                            }
                            //否则进行下载
                            ResponseBody responseBody =  response.body();
                            byte[] responseContent = new byte[0];
                            try {
                                responseContent = responseBody.bytes();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            PicUtil.saveImage(url,responseContent);
                        }
                    });
                    return null;
                }
            });
        }

        return null;
    }
}
