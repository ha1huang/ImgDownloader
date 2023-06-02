package com.example.http;

import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 *
 * @author 黄天啸
 *
 */

public class HttpHelper {
    private String TAG = "HttpHelper";
    private HttpHelper(){}
    private static HttpHelper httpHelper = new HttpHelper();
    private OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .build();
    public static HttpHelper getInstance(){
        return httpHelper;
    }
    String responseBody =null;

    //get方法，设置响应处理的回调
    public void get(String url, HttpHandler httpHandler){
        Request request = new Request.Builder()
                .url(url)
                .build();
        //网络请求采取异步
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //这里的异常就不返回给前端了
                if (e instanceof SocketTimeoutException) {
                    // 处理连接超时或读取数据超时异常，可以在这里重新发送请求
                    Log.d(TAG, "onFailure: 接超时或读取数据超时异常",e);
                } else if (e instanceof IOException) {
                    // 处理网络连接出现问题，例如网络不可用或服务器无法访问等异常，可以在这里重新发送请求
                    Log.d(TAG, "onFailure: 网络异常",e);
                } else {
                    // 处理其他类型的异常，可以通过打印日志的方式进行排查和分析
                    Log.e(TAG, "请求失败", e);
                }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                // 处理响应结果,直接外包给HttpHandler。其实onFailure也可以外包。这里没必要。
                httpHandler.handleGetResponse(response);
            }
        });
    }
}
